/* Program name: mo5 progamming assignment
*  Author: noah gutierrez
*  Date last updated: 5/1/2024
*/
#include <iostream>
#include <string>
#include <sqlite3.h>

static int callback(void *data, int argc, char **argv, char **azColName) {
    for (int i = 0; i < argc; i++) {
        std::cout << azColName[i] << ": " << (argv[i] ? argv[i] : "NULL") << std::endl;
    }
    std::cout << std::endl;
    return 0;
}

void viewCustomer(sqlite3 *db) {
    std::string sql = "SELECT customer_id, first_name, last_name FROM customer;";
    sqlite3_stmt *stmt;
    sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, 0);

    std::cout << "Customer List:\n";
    int rc = sqlite3_step(stmt);
    while (rc == SQLITE_ROW) {
        int customerId = sqlite3_column_int(stmt, 0);
        std::string firstName = sqlite3_column_text(stmt, 1);
        std::string lastName = sqlite3_column_text(stmt, 2);
        std::cout << customerId << " - " << lastName << ", " << firstName << "\n";
        rc = sqlite3_step(stmt);
    }

    sqlite3_finalize(stmt);

    std::cout << "Enter the ID of the customer to view: ";
    int choice;
    std::cin >> choice;

    // Input validation
    while (std::cin.fail() || choice < 1) {
        std::cerr << "Invalid input. Please enter a valid ID.\n";
        std::cin.clear();
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        std::cout << "Enter the ID of the customer to view: ";
        std::cin >> choice;
    }

    sql = "SELECT c.first_name, c.last_name, c.phone, a.address, a.district, a.city_id, ct.name, c.email, c.active, c.last_update "
          "FROM customer c "
          "JOIN address a ON c.address_id = a.address_id "
          "JOIN city ct ON a.city_id = ct.city_id "
          "WHERE c.customer_id = ?;";
    sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, 0);
    sqlite3_bind_int(stmt, 1, choice);

    rc = sqlite3_step(stmt);
    if (rc == SQLITE_ROW) {
        std::string firstName = sqlite3_column_text(stmt, 0);
        std::string lastName = sqlite3_column_text(stmt, 1);
        std::string phone = sqlite3_column_text(stmt, 2);
        std::string address = sqlite3_column_text(stmt, 3);
        std::string district = sqlite3_column_text(stmt, 4);
        int cityId = sqlite3_column_int(stmt, 5);
        std::string cityName = sqlite3_column_text(stmt, 6);
        std::string email = sqlite3_column_text(stmt, 7);
        int active = sqlite3_column_int(stmt, 8);
        std::string lastUpdate = sqlite3_column_text(stmt, 9);

        std::cout << "Name: " << lastName << ", " << firstName << "\n";
        std::cout << "Phone: " << phone << "\n";
        std::cout << "Address: " << address << ", " << district << ", " << cityName << ", " << cityId << "\n";
        std::cout << "Email: " << email << "\n";
        std::cout << "Active: " << (active == 1 ? "Yes" : "No") << "\n";
    } else {
        std::cerr << "Error executing the query. " << sqlite3_errmsg(db) << "\n";
    }
}
