# GameVault

GameVault is a command-line accounting ledger written in Java. It records deposits and payments, saves them to a CSV file, and provides filtered views and date-based reports so you can see where the money is going.

## Features

- Record deposits and payments with a description, vendor, and amount
- Timestamp new transactions automatically
- Keep transaction history between runs by saving to a pipe-delimited CSV file
- View all transactions, deposits only, or payments only
- Check the current balance
- Run reports for month to date, previous month, year to date, and previous year
- Search by vendor using case-insensitive, partial matching
- Reject amounts that are negative, zero, or not valid numbers

## Project Structure

```text
src/main/
├── java/com/pluralsight/
│   ├── Main.java          # Starts the app and loads saved transactions
│   ├── MenuScreen.java    # Handles menus and user input
│   ├── Ledger.java        # Handles transaction logic, reports, and file access
│   └── Transactions.java  # Represents a single transaction
└── resources/
    └── transactions.csv   # Stores transaction history
```

## Getting Started

### Requirements

- Java 14 or later
- A `transactions.csv` file at `src/main/resources/`

### Run the App

The CSV path is relative, so run the app from the project root.

**In an IDE:** Open the project and run `Main.java`.

**From the terminal:**

```bash
javac -d out src/main/java/com/pluralsight/*.java
java -cp out com.pluralsight.Main
```

## Using GameVault

### Home Menu

| Option | Action |
| --- | --- |
| `P` | Make a payment |
| `D` | Make a deposit |
| `L` | Open the ledger |
| `X` | Exit |

### Ledger Menu

| Option | Action |
| --- | --- |
| `A` | View all transactions, newest first |
| `D` | View deposits |
| `P` | View payments |
| `B` | View the current balance |
| `R` | Open reports |
| `H` | Return to the home menu |

### Reports Menu

| Option | Action |
| --- | --- |
| `1` | Month to date |
| `2` | Previous month |
| `3` | Year to date |
| `4` | Previous year |
| `5` | Search by vendor |
| `0` | Return to the ledger menu |

## Data Format

Transactions are stored in `transactions.csv` as pipe-delimited rows:

```text
date|time|description|vendor|amount
2026-11-06|09:30:00|Console sale|Nintendo|299.99
2026-11-06|14:15:00|Store restock|Game Supply Co|-120.50
```

- Dates use the `yyyy-MM-dd` format, and times use `HH:mm:ss`.
- Deposits are stored as positive amounts, and payments as negative amounts.
- The first line is treated as a header and skipped when the file loads, so keep a header row in place.

## How It Works

1. `Main` creates the `Ledger`, loads saved transactions, and opens the menu.
2. `MenuScreen` collects the transaction details and passes them to `Ledger`.
3. `Ledger` validates the amount and appends the transaction to the CSV file.
4. If the save succeeds, the transaction is added to the in-memory list used for balances, views, and reports.
