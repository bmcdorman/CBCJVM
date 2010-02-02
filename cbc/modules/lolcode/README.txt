Java LOLCODE - LOLCODE parser and interpreter (http://lolcode.com/)
Copyright (C) 2007  Brett Kail (bkail@iastate.edu)
http://bkail.public.iastate.edu/lolcode/

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.


Implementation
--------------
- Specifications
  - 1.0 - Fully supported.  See section "Specification: 1.0"
  - 1.1 - Fully supported.  See section "Specification: 1.1"
  - 1.2 - Fully supported.  See section "Specification: 1.2"
  - 1.3 - See section "Specification: 1.3"
  - See section "Specification Ambiguities" for this implementation's
    resolution of some specification ambiguities.
- Extensions
  - See section "Implementation: HAI"
  - See section "Implementation: CAN HAS"
  - See section "Module: JAVA"
- Compilation
  - Java 1.5 is required for the javax.script implementation.
  - ICU4J must be present in the lib/ directory.  ICU4J is used for named
    character support.
- Execution
  - Java 1.5 is required.
  - ICU4J must be present on the classpath for named character support.  If it
    is not found at runtime, the implementation gracefully rejects LOLCODE
    programs that use named characters.
- Usage
  - java -jar build/lolcode.jar FILE
    - Simple execution
  - java -classpath build/lolcode.jar:icu4j.jar org.stummies.lolcode.Main FILE
    - Execution including ICU4j
- See section "TODO" for future enhancement possibilities.


Specification: 1.0
------------------
1. Statements are separated by [\n.]+
2. CAN HAS <module/"file">?
   - See section "Implementation: CAN HAS".
3. GIMMEH
4. HAI
5. KTHXBYE
6. DIAF
7. BYES
8. KTHX
9. IM IN YR
10. VISIBLE <stuff>[!]
11. I HAS A
    - If ITZ is specified, the variable is assigned the specified value.
      Otherwise, the variable is assigned NOOB.
12. [## IN MAH]* <var>
    - Any expression is allowed rather than just literal NUMBR.  If the
      expression is not NUMBR or NUMBAR, an exception is thrown.
    - If a slot less than 0 is accessed or assigned, an exception is thrown.
    - When a variable is expanded, empty slots are set to NOOB.
    - If a slot greater than or equal to the size of the BUKKIT is accessed, an
      exception is thrown.
13. LOL
    - If no index is specified, index 0 is used.
14. IZ
15. Comparison operators
    - Operations involving non-NUMBR (including NOOB) result in an exception as
      in specification 1.1.
    - Operations are evaluated as in specification 1.2.
16. Logical/Bitwise operators
    - Operations are evaluated as in specification 1.2.
17. Operators
    - Operations involving non-NUMBR (including NOOB) result in an exception as
      in specification 1.1.
    - Operations are evaluated as in specification 1.2.
    - Operator precedence is (highest to lowest):
      - TIEMZ, OVARZ
      - UP, NERF
      - IN MAH
      - Comparison
      - Logical/Bitwise


Specification: 1.1
------------------
Specification 1.1 is parsed with all operators from specification 1.0.

+ CREATING A LOLCODE FILE
  - See section "Implementation: HAI"
+ TYPING AND VARIABLE DECLARATIONS
+ KEYWORDS
+ IDENTIFIERS
+ COMMENTS
  - See section "Implementation: OBTW".
+ UNINITIALIZED AND NULL VALUES
+ CONDITIONALS
  + IF-THEN
    - IZ from specification 1.0 is supported.
  + SWITCHES
+ INPUT AND OUTPUT
+ ARRAYS AND HASHES
  - IN MAH creates BUKKITs as in specification 1.3.
+ GOTO


Specification: 1.2
------------------
+ Formtting
  + whitespace
    1. Spaces
       - HORIZONTAL TABULATION
       - LINE FEED
       - VERTICAL TABULATION
       - FORM FEED
       - CARRIAGE RETURN
       - FILE SEPARATOR
       - GROUP SEPARATOR
       - RECORD SEPARATOR
       - UNIT SEPARATOR
       - Unicode space character that is not also a non-breaking space
    2. Multiple spaces
    3. Indentation
    4. Commands
    5. Newlines (CR, LF, CRLF)
    6. Multiple commands (,)
    7. Multiple lines (... and u2026)
    8. Multiple lines
    9. Line continuation and empty lines
    10. Empty command continuation
    11. Single-line comments
    12. Unterminated string literal
  + comments
    - See section "Implementation: OBTW".
  + file creation
    - See section "Implementation: HAI".

+ Variables
  + scope
    - Variables are scoped to their containing statement block.  Variables
      defined in an O RLY, WTF, or IM IN YR are no longer visible after the
      block has closed.
  + naming
  + declaration and assignment

+ Types
  + untyped
    - Implicit cast to NUMBR, NUMBAR, or YARN results in an exception.
  + booleans
    - WIN is cast to 1, 1.0, and "WIN".
    - FAIL is cast to 0, 0.0, and "".
  + numerical types
  + strings
    - :{<var>} explicitly casts to YARN.
    - :[<char name>] is implemented using ICU4J.  ICU4J classes must be visible
      (i.e., be on the class path).
  + arrays
    - There is no support for BUKKIT in specification 1.2.
  + types

+ Operators
  + calling syntax and precedence
  + math
    - YARNs are implicitly cast as specified.
    - Non-YARNs are implicitly cast to NUMBR or NUMBAR.
  + booleans
    - Operands are implicitly cast to TROOF.
  + comparison
    - NOOB is equal to NOOB.
  + concatenation
    - Operands are implicitly cast to YARN.
  + casting

+ Input/Output
  + terminal-based
    - Arguments to VISIBLE and INVISIBLE are explicitly cast to YARN.

+ Statements
  + expression statements
  + assignment statements

+ Flow control
  + if-then
  + case
  + loops
    - TIL and WILE expressions are evaluated at the end of the loop only.  In
      other words, the statements within a loop will execute at least once.
    - The variable may be modified in the loop.  At the end of the loop
      statements, the following statement is executed
        <variable> R SUM|DIFF OF <variable> 1

+ Functions
  + definition
  + returning
  + calling


Specification: 1.3
------------------
Specification 1.3 is an extended version of specification 1.2.  It includes
implementations of the mature proposals from 1.3.

+ bukkit
  - A new value type FUNCTION is added
  + Prelude
    - The expression A <type> can be used anywhere to create NOOB, FAIL, 0,
      0.0, "", or an empty BUKKIT.
  + BUKKITs
  + Declaration
  + Old commands in the context of objects
  + Slot access
    - "!?", u203d, and u2049 are supported.
    - When a FUNCTION slot is accessed, it is called with no arguments.
    - A FUNCTION can be called with arguments using the following syntax
      (command-break ends all open function calls)
        <slot-access> [WIF|OF] <expression> [[AN] <expression>][!| MKAY]
    - Accessing a slot that does not exist results in an exception.
    - If specification 1.0 or specification 1.1 are active, accessing a slot
      that does not exist using IN MAH results in NOOB.
  + Scope
    - Slot functions declared using HOW DUZ <object> have no defined namespace
      scope.  Therefore, they have no access to variables in their containing
      scope, and the MAH keyword cannot be used.

+ exception
  + Handling Exceptions
    - The exception type is stored in IT as a YARN.  This disagrees with the
      example which shows the type being accessed as a slot of a BUKKIT.
  + Raising Exceptions
    - The RTFM expression is implicitly cast to YARN
  - The following exception types are pre-defined:
    - BadArgumentCount - Calling a slot function with the wrong arguments
    - BadBUKKITType - Using !! on a non-BUKKIT value
    - BadBUKKITUse - Casting a BUKKIT to any other type
    - BadCallType - Using WIF on a non-FUNCTION value
    - BadInMahType - Using IN MAH with a non-numeric index value
    - BadIndex - Using IN MAH with an out-of-bounds index
    - BadIO - If an I/O error occurs using GIMMEH
    - BadJavaClass - See section "Module: JAVA"
    - BadJavaType - See section "Module: JAVA"
    - BadJavaUse - See section "Module: JAVA"
    - BadLIEKType - Using LIEK on a non-BUKKIT value
    - BadMathType - Using a 1.0 operator on a non-NUMBR non-YARN value
    - BadMOD - 1 % 0
    - BadNOOBUse - Implicitly casting a NOOB to NUMBR, NUMBR, or YARN
    - BadNUMBRFormat - Casting a YARN with the wrong format to a NUMBR
    - BadNUMBARFormat - Casting a YARN with the wrong format to a NUMBAR
    - BadQuoshunt - 1 / 0
    - BadSlot - Accessing an undeclared BUKKIT slot

+ loop2
  + general form
  + initialization & termination
  + nested loops
  + labels
  + breaks & continues
  + loop variable & operation
    - In-scope slot functions and slot accessed (OBJECT!!SLOT) may be used.
    - Variables declared with YR are assigned a default value of 0 not NOOB,
      which allows them to be used in math and comparison operators.
  + for-each
    - WATCHIN iterates over slots in the order they were assigned.
  + variable scope
  + conditional execution


Specification ambiguities
-------------------------
1.  In math section, the "AN" operators are not surrounded by "[" "]".
    - This implementation treats AN as optional.
2.  The use of "cast" must be qualified by "explicit" or "implicit" to know how
    to handle NOOB values.  In the boolean section, the phrase "automatically
    cast" is used, which probably means "implicit".  Presumably, interpolated
    strings use an explicit cast to YARN?
    - This document specifies the casting behavior in all ambiguous cases.
3.  What happened to BYES? DIAF?
    - This implementation does not allow BYES or DIAF unless specification 1.0
      or specification 1.1 is available via HAI or CAN HAS.
4.  What are the cast semantics of BUKKIT?
    - This implementation throws BadBukkitUse.
5.  BOTH FAIL MYFUNC BTW call MYFUNC or short-circuit?
    - This implementation does not short-circuit.
6.  QUOSHUNT 0 0 BTW error?
    - This implementation throws BadQUOSHUNT
7.  In bukkit specification 1.3, the "Declaration" section uses the A keyword,
    but the O HAI IM section does not.
    - This implementation follows the specification.
8.  tests for 1.2 specifies keyword QWOSHUNT
    - This implementation does not recognize QWOSHUNT as a keyword.


Implementation: HAI
-------------------
1.0, 1.1, 1.2, 1.3
  Indicates that the specified specification should be used for parsing.

GINGER
  This is an alias for 1.1

Other tokens are not supported.  If no token is specified, then specification
version 1.2 is used.


Implementation: CAN HAS
-----------------------
1.0, 1.1, 1.2, 1.3
  Indicates that features from the specified specification should be allowed.

GINGER
  This is an alias for 1.1.

JAVA
  See section "Module: JAVA"

STDIO
  This request has no effect and is ignored.  The VISIBLE, INVISIBLE, and
  GIMMEH statements are always available.

If a YARN literal is used, then file inclusion is used.  A file included in
this manner must begin with HAI and end with KTHXBYE, but the contents of that
block are otherwise textually included at the point of use of CAN HAS.
Statement blocks that are opened in one file must be closed in the same file.


Implementation: OBTW
--------------------
OBTW is terminated by a newline followed by zero or more whitespace characters
followed by TLDR followed by a command-break.


Module: JAVA
------------
+ Usage
  - This module introduces a new expression:
      JAVA <expression>
    The expression operand is implicitly cast to YARN, and the result is passed
    to Class.forName.  If the class cannot be found, BadJavaClass is thrown.
    The result of the expression is a JAVA value.

+ JAVA value
  - JAVA values wrap a Java object, which includes arrays and Class objects.
    JAVA values can be implicitly cast to YARN, which returns the result of
    calling toString() on the underlying object.  Attempting to cast a JAVA
    value to TROOF, NUMBR, or NUMBAR results in BadJavaUse.
  - Fields and methods of the underlying object can be accessed by using BUKKIT
    slot syntax.  Fields cannot be declared.  When setting a field or passing
    method arguments, LOLCODE values are converted to Java values (see "LOLCODE
    to Java conversion").  When a field value or method value is returned, Java
    values are converted to LOLCODE values (see "Java to LOLCode conversion").
  - If a method slot is found but none of the methods have the appropriate
    number of arguments, BadArgumentCount is thrown.  If a method slot is found
    but none of the methods can be called because an argument fails to be
    converted, then BadJavaType is thrown.
  - If an exception is thrown while executing a method, the type of the
    exception for an O NOES clause is the class name of the exception.
  - If a JAVA value represents a Class, then only static members of the class
    can be accessed.  Otherwise, both static and non-static members of the
    object's class can be accessed.
  - If a value represents a class, a slot named "new" is available.
    - If the class is an array, then the slot may be called with a single
      argument that is implicitly cast to NUMBR that specifies the size of the
      array.
    - Otherwise, the slot may be called, and a constructor is selected using
      the same algorithm as method slots.
  - If a value represents an array
    - Numerical indices can be used.  If the index is out of bounds, then
      BadSlot is thrown.
    - A slot named "length" is available that returns the length of the array.
  - Attempting to assign a method slot, the "new" slot of a class, or the
    "length" slot of an array, then BadSlot is thrown.

+ LOLCODE to Java conversion
  - If the value is NOOB, it is converted to null.  If the target type is a
    primitive type, BadJavaType is thrown.
  - If the value is JAVA, it is unwrapped.  If the target type is
    incompatible, BadJavaType is thrown.
  - boolean, Boolean - implicit cast to TROOF
  - byte, Byte, short, Short, char, Character, int, Integer, long, Long -
    implicit cast to NUMBR
  - float, Float, double, Double - implicit cast to NUMBR
  - String - implicit cast to YARN
  - Object
    - TROOF - Boolean
    - NUMBR - Integer
    - NUMBAR - Float
    - YARN - String
  - BadJavaType is thrown.

+ Java to LOLCODE conversion
  - null - NOOB
  - Long, Double, String - YARN
  - Float - NUMBAR
  - Number - NUMBR
  - All other values are converted as a JAVA value


TODO
----
+ Byte-code compilation for both class files and dynamic class creation.
+ O HAI IM VAR IM LIEK JAVA "..."
+ javax.script

