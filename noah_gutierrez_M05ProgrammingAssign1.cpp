/* Program name: mo5 progamming assignment
*  Author: noah gutierrez
*  Date last updated: 5/1/2024
*/
#include <iostream>
#include <sqlite3.h>

static int callback(void *data, int argc, char **argv, char **azColName) {
    for (int i = 0; i < argc; i++) {
        std::cout << azColName[i] << ": " << (argv[i] ? argv[i] : "NULL") << std::endl;
    }
    std::cout << std::endl;
    return 0;
}

int main() {
    sqlite3 *db;
    int rc;

    rc = sqlite3_open("chinook.db", &db);

    if (rc) {
        std::cerr << "Error in connection: unable to open database file" << std::endl;
        return rc;
    } else {
        std::cout << "Successfully opened the database" << std::endl;
    }

    const char *sql = "SELECT Albums.Title, Tracks.Name "
                      "FROM Albums "
                      "JOIN Tracks ON Albums.AlbumId = Tracks.AlbumId "
                      "ORDER BY Albums.Title, Tracks.TrackId;";

    rc = sqlite3_exec(db, sql, callback, 0, 0);

    if (rc != SQLITE_OK) {
        std::cerr << "Error executing query: " << sqlite3_errmsg(db) << std::endl;
        sqlite3_close(db);
        return rc;
    }

    sqlite3_close(db);
    return 0;
}
