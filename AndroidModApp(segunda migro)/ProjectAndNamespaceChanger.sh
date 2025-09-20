#!/bin/bash

# Check parameters
if [[ $# -ne 2 ]]; then
  echo "Usage: $0 <new_project_name> <new_namespace>"
  exit 1
fi

NEW_PROJECT_NAME="$1"
NEW_NAMESPACE="$2"

# Get old project name
if [[ -f "settings.gradle" ]]; then
  OLD_PROJECT_NAME=$(grep "^rootProject.name" settings.gradle | sed -E "s/.*=['\"](.*)['\"].*/\1/")
elif [[ -f "settings.gradle.kts" ]]; then
  OLD_PROJECT_NAME=$(grep "^rootProject.name" settings.gradle.kts | sed -E "s/.*=[[:space:]]*['\"](.*)['\"].*/\1/")
else
  OLD_PROJECT_NAME="Not found"
fi

# Get old namespace from the first module found
if [[ -f "./app/build.gradle" ]]; then
  OLD_NAMESPACE=$(grep "namespace" ./app/build.gradle | sed -E "s/.*namespace[[:space:]]*=[[:space:]]*['\"](.*)['\"].*/\1/")
elif [[ -f "./app/build.gradle.kts" ]]; then
  OLD_NAMESPACE=$(grep "namespace" ./app/build.gradle.kts | sed -E "s/.*namespace[[:space:]]*=[[:space:]]*['\"](.*)['\"].*/\1/")
else
  OLD_NAMESPACE="Not found"
fi

# Remove the last segment after the dot
if [[ "$OLD_NAMESPACE" != "Not found" ]]; then
  OLD_NAMESPACE_BASE=${OLD_NAMESPACE%.*}
else
  OLD_NAMESPACE_BASE="Not found"
fi

echo "Old project name: $OLD_PROJECT_NAME"
echo "New project name: $NEW_PROJECT_NAME"
echo "Old base namespace: $OLD_NAMESPACE_BASE"
echo "New namespace: $NEW_NAMESPACE"

# Cambiar el namespace base en los build.gradle y build.gradle.kts
if [[ "$OLD_NAMESPACE_BASE" != "Not found" ]]; then
  for file in $(find . -name "build.gradle" -o -name "build.gradle.kts"); do
    if grep -q "namespace" "$file"; then
      current_ns=$(grep "namespace" "$file" | sed -E "s/.*namespace[[:space:]]*=[[:space:]]*['\"](.*)['\"].*/\1/")
      suffix=${current_ns#"$OLD_NAMESPACE_BASE"}
      new_ns="$NEW_NAMESPACE$suffix"
      sed -i -E "s/(namespace[[:space:]]*=[[:space:]]*)['\"][^'\"]*['\"]/\\1\"$new_ns\"/" "$file"
      echo "Namespace actualizado en $file: $new_ns"
    fi
  done
else
  echo "No se encontró el namespace base antiguo, no se realizaron cambios en los build.gradle."
fi

# Cambiar el group en build-logic/convention/build.gradle.kts
CONVENTION_BUILD_GRADLE="build-logic/convention/build.gradle.kts"
if [[ -f "$CONVENTION_BUILD_GRADLE" ]]; then
  sed -i -E "s|^group = \".*\"|group = \"$NEW_NAMESPACE.build-logic\"|" "$CONVENTION_BUILD_GRADLE"
  echo "Group actualizado en $CONVENTION_BUILD_GRADLE: $NEW_NAMESPACE.build-logic"
else
  echo "No se encontró $CONVENTION_BUILD_GRADLE, no se realizó el cambio de group."
fi