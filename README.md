# Nextory
## _Coding Interview - Android - Jacques Giraudel_


## Problem 1: book detail screen

- Passed the selected book from the list to the detail using the id to respect the single source of truth pattern (the repository is the source) / Google reco
- Used a flow for BookDetail to anticipate the 2nd problem (otherwise a live data could have been sufficient at this stage)
- Added preview for book list row and book detail sheet

## Problem 2: book favorite

- as the database is local and there is not user management, I just added a favorite column to the book table
- added a migration specification for the database