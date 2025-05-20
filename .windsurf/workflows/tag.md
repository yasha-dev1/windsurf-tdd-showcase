---
description: Tagging to build new version
---

add tag to github to build new version in the current branch.
Judge based on the changes in the commits from the last tag to the current commit, if there should be minor version update or patch version. the tag is in the format "app/{semver_version}" where semver version is in the format 0.0.0. start from 1.0.0