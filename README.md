# introCS_ex2

# Spreadsheet Project - README

## Overview
This project implements a simple spreadsheet system that supports the evaluation and manipulation of cell data in a grid-like structure. The spreadsheet supports text, numbers, and formulae with basic arithmetic operations. The implementation is modular, allowing for extensibility and clear separation of concerns between the spreadsheet and the individual cells.

The project contains two main classes:
1. `SCell` - Represents a single cell in the spreadsheet.
2. `Ex2Sheet` - Represents the spreadsheet itself.

---

## Features
- **Cell Types**: Each cell can hold either text, a number, or a formula (starting with `=`).
- **Formula Evaluation**: Supports arithmetic operations such as addition, subtraction, multiplication, and division.
- **Validation**: Ensures that cell contents conform to their respective types.
- **File Operations**: Load and save spreadsheet data from/to a file.
- **Customizable Grid Size**: The spreadsheet supports grids of any size.

---

## Classes and Functions

### 1. `SCell` (Single Cell Implementation)
The `SCell` class implements the `Cell` interface and represents the basic building block of the spreadsheet.

#### **Constructor**
- `SCell(String s)`: Initializes a new cell with the provided string `s`. Automatically determines the cell type (text, number, or formula).

#### **Core Methods**
- `void setData(String s)`: Sets the data for the cell and updates its type.
- `String getData()`: Retrieves the raw data stored in the cell.
- `int getType()`: Returns the type of the cell:
  - 1: Text
  - 2: Number
  - 3: Formula
- `void setType(int t)`: Sets the type of the cell manually.
- `boolean isNumber(String text)`: Checks if the given string represents a valid number.
- `boolean isForm(String text)`: Checks if the given string is a valid formula (starting with `=`).
- `boolean IsText(String text)`: Checks if the given string is valid text.
- `int whichType(String s)`: Determines the type of the string (`1` for text, `2` for numbers, `3` for formula).

#### **Evaluation Methods**
- `String toString()`: Returns the evaluated result of the cell (for numbers and formulas) or the raw data for text.
- `static boolean validateOperators(String str)`: Ensures that the operators in a formula are used correctly.
- `static double evaluateExpression(String expression)`: Evaluates arithmetic expressions from formulae using operator precedence.

---

### 2. `Ex2Sheet` (Spreadsheet Implementation)
The `Ex2Sheet` class implements the `Sheet` interface and provides the main functionality for the spreadsheet.

#### **Constructors**
- `Ex2Sheet(int x, int y)`: Creates a spreadsheet with dimensions `x` by `y`.
- `Ex2Sheet()`: Creates a default spreadsheet using predefined constants for width and height.

#### **Core Methods**
- `void set(int x, int y, String s)`: Sets the content of a cell at position `(x, y)` with the string `s`.
- `Cell get(int x, int y)`: Retrieves the cell object at position `(x, y)`.
- `Cell get(String coords)`: Retrieves the cell object at the given coordinates in string format.
- `int width()`: Returns the width of the spreadsheet.
- `int height()`: Returns the height of the spreadsheet.
- `boolean isIn(int x, int y)`: Checks if the given coordinates `(x, y)` are within the bounds of the spreadsheet.

#### **Evaluation Methods**
- `String value(int x, int y)`: Evaluates the value of the cell at position `(x, y)` and returns it as a string.
- `void eval()`: Evaluates all formulae in the spreadsheet.
- `String eval(int x, int y)`: Evaluates the specific cell at `(x, y)` and returns its value.
- `int[][] depth()`: Calculates the dependency depth of formulae in the spreadsheet for evaluation order.

#### **File Operations**
- `void load(String fileName)`: Loads a spreadsheet configuration from a file.
- `void save(String fileName)`: Saves the current spreadsheet configuration to a file.

---
