///*
// * CS3210 - Principles of Programming Languages - Fall 2020
// * Instructor: Thyago Mota
// * Description: Prg01 - Syntax Analyzer
// * Student(s) Name(s):
// */
//
//class SyntaxAnalyzer(private var source: String) {
//
//  private var it = new LexicalAnalyzer(source).iterator
//  private var lexemeUnit: LexemeUnit = null
//
//  private def getLexemeUnit() = {
//    if (lexemeUnit == null)
//      lexemeUnit = it.next()
//  }
//
//  def parse(): Tree = {
//    parseProgram()
//  }
//
//
//
//
//
//  // TODO: finish the syntax analyzer
//  // program = `program` identifier body `.`
//  private def parseProgram() = {
//    // create a tree with label "program"
//    val tree = new Tree("program")
//    getLexemeUnit()
//    if (lexemeUnit.getToken() == Token.PROGRAM) {
//      tree.add(new Tree(lexemeUnit.getLexeme()))
//      lexemeUnit = null
//      getLexemeUnit()
//      tree.add(parseIdentifier())
//      tree.add(parseBody)
//      if (lexemeUnit.getToken() == Token.PERIOD) {
//        tree.add(new Tree(lexemeUnit.getLexeme()))
//        lexemeUnit = null
//        getLexemeUnit()
//      }
//      else
//        throw new Exception("Syntax Analyzer Error: period expected!")
//    }
//    else
//      throw new Exception("Syntax Analyzer Error: program expected!")
//    // return the tree
//    tree
//  }
//
//  // identifier = letter { ( letter | digit ) }
//  // TODO: return a new tree with the label "identifier" followed by the actual lexeme
//  private def parseIdentifier() = new Tree("identifier: '" + lexemeUnit.getLexeme() + "'")
//
//
//  // body = [ var_sct ] block
//
//
//  // var_sct = ´var´ var_dcl { ´;´ var_dcl }
//  private def parse_varSct(): Tree = {
//    //    println("parseDefinitionList")
//    val tree = new Tree("var_sct")
//    getLexemeUnit()
//
//    if (lexemeUnit.getToken() == Token.VAR) {
//      tree.add(new Tree(lexemeUnit.getLexeme()))
//      lexemeUnit = null
//      getLexemeUnit()
//    }
//
//    var done = false
//    while (!done) {
//      tree.add(parse_valDlc())
//      if (lexemeUnit.getToken() == Token.SEMI_COL) {
//        tree.add(new Tree(lexemeUnit.getLexeme()))
//        lexemeUnit = null
//        getLexemeUnit()
//      }
//      else
//        done = true
//    }
//    tree
//    }
//
//
//
//
//  // var_dcl = identifier { identifier } ´:´ type
//  private def parse_valDlc(): Tree = {
//    //    println("parseSingleDefinition")
//    val tree = new Tree("single-definition")
//    getLexemeUnit()
//    var done = false
//    while (!done) {
//      tree.add(parseIdentifier())
//      //      println(lexemeUnit)
//      if (lexemeUnit.getToken() == Token.IDENTIFIER) {
//        tree.add(new Tree(lexemeUnit.getLexeme()))
//        lexemeUnit = null
//        getLexemeUnit()
//        done = true
//      }
//      else if (lexemeUnit.getToken() == Token.COL ||
//        lexemeUnit.getToken() == Token.PUNCTUATOR)
//        done = true
//    }
//    tree
//  }
//
//
//  // type = ´Integer´ | ´Boolean´
//  private def parseType(): Tree = {
//    // TODO: create a tree with label "term'"
//    val tree = new Tree("type")
//
//    // TODO: call getLexemeUnit
//    getLexemeUnit()
//
//    // TODO: if token is NOT EOF
//    if (lexemeUnit.getToken() != Token.EOF) {
//
//      // TODO: if token is "*" or "/", add token as new branch and reset lexemeUnit;
//      //  then add result of "parseFactor" and "parseTermPrime" as new branches
//      if (lexemeUnit.getToken() == Token.INTEGER || lexemeUnit.getToken() == Token.BOOLEAN) {
//        tree.add(new Tree(lexemeUnit.getLexeme()))
//        lexemeUnit = null // always set lexemeUnit to null after consuming it
//      }
//    }
//    tree
//  }
//
//
//      // block = ´begin´ stmt { ´;´ stmt } ´end´
//      private def parseBlock(): Tree = {
//        //    println("parseDefinitionList")
//        val tree = new Tree("block")
//        getLexemeUnit()
//
//        if (lexemeUnit.getToken() == Token.BEGIN) {
//          tree.add(new Tree(lexemeUnit.getLexeme()))
//          lexemeUnit = null
//          getLexemeUnit()
//        }
//
//        var done = false
//        while (!done) {
//          tree.add(parseStmt())
//          if (lexemeUnit.getToken() == Token.SEMI_COL) {
//            tree.add(new Tree(lexemeUnit.getLexeme()))
//            lexemeUnit = null
//            getLexemeUnit()
//          }
//          else
//            done = true
//        }
//        tree
//      }
//      // stmt = assgm_stmt | read_stmt | write_stmt | if_stmt | while_stmt | block
////      private def parseStmt(): Tree = {
////        //    println("parseTerm")
////        val tree = new Tree("stmt")
////        getLexemeUnit()
////        //    println(lexemeUnit)
////        if (lexemeUnit.getToken() == Token.OPEN_BRACKET)
////          tree.add(parseOptionalSequence())
////        else if (lexemeUnit.getToken() == Token.OPEN_BRACE)
////          tree.add(parseRepeatedSequence())
////        else if (lexemeUnit.getToken() == Token.OPEN_PAR)
////          tree.add(parseGroupedSequence())
////        else if (lexemeUnit.getToken() == Token.META_IDENT || lexemeUnit.getToken() == Token.TERMINAL_STRING)  {
////          tree.add(new Tree(lexemeUnit.getLexeme()))
////          lexemeUnit = null
////          getLexemeUnit()
////        }
////        else if (lexemeUnit.getToken() != Token.PIPE &&
////          lexemeUnit.getToken() != Token.NEW_LINE &&
////          lexemeUnit.getToken() != Token.CLOSE_PAR &&
////          lexemeUnit.getToken() != Token.CLOSE_BRACE &&
////          lexemeUnit.getToken() != Token.CLOSE_BRACKET
////        )
////          throw new Exception("Syntax Analyzer Error: valid term expected!")
////        tree
////      }
//
//      // assgm_stmt = identifier ´:=´ expr
//      private def parse_assgmStmt(): Tree = {
//        val tree = new Tree("assgm_stmt")
//        getLexemeUnit()
//        if (lexemeUnit.getToken() == Token.IDENTIFIER) {
//          tree.add(new Tree(lexemeUnit.getLexeme()))
//          lexemeUnit = null
//          getLexemeUnit()
//          if (lexemeUnit.getToken() == Token.PUNCTUATOR) { //Be more specific?
//            tree.add(new Tree(lexemeUnit.getLexeme()))
//            lexemeUnit = null
//            getLexemeUnit()
//            tree.add(parseExpr())
//          }
//          else
//            throw new Exception("Syntax Analyzer Error: defining symbol expected!")
//        }
//        else
//          throw new Exception("Syntax Analyzer Error: meta identifier expected!")
//        tree
//      }
//
//      // read_stmt = ´read´ identifier
//      private def parseread_Stmt() = {
//        // create a tree with label "program"
//        val tree = new Tree("read")
//        getLexemeUnit()
//        if (lexemeUnit.getToken() == Token.READ) {
//          tree.add(new Tree(lexemeUnit.getLexeme()))
//          lexemeUnit = null
//          getLexemeUnit()
//          tree.add(parseIdentifier())
//        }
//        else
//          throw new Exception("Syntax Analyzer Error: program expected!")
//        // return the tree
//        tree
//      }
//
//
//      // write_stmt = ´write´ ( identifier | literal )
//      private def parsewrite_Stmt(): Tree = {
//        // TODO: create a tree with label "expr"
//        val tree = new Tree("write_stmt")
//
//        // TODO: call getLexemeUnit
//        getLexemeUnit()
//
//
//        if (lexemeUnit.getToken() == Token.WRITE) {
//          tree.add(new Tree(lexemeUnit.getLexeme()))
//          lexemeUnit = null
//          getLexemeUnit()
//          tree.add(parseIdentifier())
//
//          // TODO: if token is NOT EOF
//          if (lexemeUnit.getToken() != Token.EOF) {
//            // TODO: if token is an identifier, add result of "parseIdentifier" as new branch and reset lexemeUnit
//            if (lexemeUnit.getToken() == Token.IDENTIFIER) {
//              tree.add(parse_arithm_Expr())
//              lexemeUnit = null // always set lexemeUnit to null after consuming it
//            }
//            // TODO: if token is a bool_expre, add result of "parseLiteral" as new branch and reset lexemeUnit
//            else if (lexemeUnit.getToken() == Token.TRUE ||lexemeUnit.getToken() == Token.FALSE) {
//              tree.add(parse_boolExpr())
//              lexemeUnit = null // always set lexemeUnit to null after consuming it
//            }
//            // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//            else
//              throw new Exception("Syntax Analyzer Error: identifier, literal or \"opening parenthesis\" expected!")
//          }
//          // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//          else
//            throw new Exception("Syntax Analyzer Error: identifier, literal or \"opening parenthesis\" expected!")
//        }
//        // TODO: return the tree
//          tree
//        }
//      // if_stmt = ´if´ bool_expr ´then´ stmt [ ´else´ stmt ]
//      private def parse_if_Stmt(): Tree = {
//        //    println("parseOptionalSequence")
//        val tree = new Tree("if_stmnt")
//        getLexemeUnit()
//        if (lexemeUnit.getToken() == Token.IF) {
//          tree.add(new Tree(lexemeUnit.getLexeme()))
//          lexemeUnit = null
//          getLexemeUnit()
//          tree.add(parse_bool_Expr())
//          if (lexemeUnit.getToken() == Token.THEN) {
//            tree.add(new Tree(lexemeUnit.getLexeme()))
//            lexemeUnit = null
//            getLexemeUnit()
//            tree.add(parse_stmt())
//            tree.add(parse_valDlc())
//            if (lexemeUnit.getToken() == Token.ELSE) {
//              tree.add(new Tree(lexemeUnit.getLexeme()))
//              lexemeUnit = null
//              getLexemeUnit()
//            }
//            else
//              throw new Exception("Syntax Analyzer Error: \"else\" expected!")
//          }
//
//          else
//            throw new Exception("Syntax Analyzer Error: \"then\" expected!")
//        }
//        else
//          throw new Exception("Syntax Analyzer Error: \"if\" expected!")
//        tree
//      }
//
//      // while_stmt = ´while´ bool_expr ´do´ stmt
//
//
//
//
//      // expr = arithm_expr | bool_expr
//      private def parseExpr(): Tree = {
//        // TODO: create a tree with label "expr"
//        val tree = new Tree("expr")
//
//        // TODO: call getLexemeUnit
//        getLexemeUnit()
//
//        // TODO: if token is NOT EOF
//        if (lexemeUnit.getToken() != Token.EOF) {
//          // TODO: if token is an identifier, add result of "parseIdentifier" as new branch and reset lexemeUnit
//          if (lexemeUnit.getToken() == Token.IDENTIFIER) {
//            tree.add(parse_arithm_Expr())
//            lexemeUnit = null // always set lexemeUnit to null after consuming it
//          }
//          // TODO: if token is a bool_expre, add result of "parseLiteral" as new branch and reset lexemeUnit
//          else if (lexemeUnit.getToken() == Token.TRUE ||lexemeUnit.getToken() == Token.FALSE) {
//            tree.add(parse_boolExpr())
//            lexemeUnit = null // always set lexemeUnit to null after consuming it
//          }
//          // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//          else
//            throw new Exception("Syntax Analyzer Error: identifier, literal or \"opening parenthesis\" expected!")
//        }
//        // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//        else
//          throw new Exception("Syntax Analyzer Error: identifier, literal or \"opening parenthesis\" expected!")
//
//        // TODO: return the tree
//        tree
//      }
//
//      // arithm_expr = term arithm_expr'
//      private def parse_arithm_Expr() = {
//        // TODO: create a tree with label "term"
//        val tree = new Tree("arithm_expr")
//
//        // TODO: call getLexemeUnit
//        getLexemeUnit()
//
//        // TODO: if token is NOT EOF, add result of "parseFactor" and "parseTermPrime" as new branches
//        if (lexemeUnit.getToken() != Token.EOF) {
//          tree.add(parseTerm())
//          tree.add(parse_arithm_expr_Prime())
//        }
//        // TODO: otherwise, throw an exception saying that "factor" was expected
//        else
//          throw new Exception("Syntax Analyzer Error: factor expected!")
//
//        // TODO: return the tree
//        tree
//      }
//
//      //arith_expr'= ( ´+´ | ´-´ ) term arithm_expr'| epsilon
//      private def parse_arithm_expr_Prime(): Tree = {
//        // TODO: create a tree with label "term'"
//        val tree = new Tree("arith_expr'")
//
//        // TODO: call getLexemeUnit
//        getLexemeUnit()
//
//        // TODO: if token is NOT EOF
//        if (lexemeUnit.getToken() != Token.EOF) {
//
//          // TODO: if token is "*" or "/", add token as new branch and reset lexemeUnit;
//          //  then add result of "parseFactor" and "parseTermPrime" as new branches
//          if (lexemeUnit.getToken() == Token.ADD_OP || lexemeUnit.getToken() == Token.SUB_OP) {
//            tree.add(new Tree(lexemeUnit.getLexeme()))
//            lexemeUnit = null // always set lexemeUnit to null after consuming it
//            tree.add(parseTerm())
//            tree.add(parse_arithm_expr_Prime())
//          }
//          // else means "epsilon" production
//        }
//
//        // TODO: return the tree
//        tree
//      }
//
//      // term = factor term'
//
//      private def parseTerm() = {
//        // TODO: create a tree with label "term"
//        val tree = new Tree("term")
//
//        // TODO: call getLexemeUnit
//        getLexemeUnit()
//
//        // TODO: if token is NOT EOF, add result of "parseFactor" and "parseTermPrime" as new branches
//        if (lexemeUnit.getToken() != Token.EOF) {
//          tree.add(parseFactor())
//          tree.add(parseTermPrime())
//        }
//        // TODO: otherwise, throw an exception saying that "factor" was expected
//        else
//          throw new Exception("Syntax Analyzer Error: factor expected!")
//
//        // TODO: return the tree
//        tree
//      }
//
//    // term' = '*'  factor term' | epsilon
//    private def parseTermPrime(): Tree = {
//      // TODO: create a tree with label "term'"
//      val tree = new Tree("term'")
//
//      // TODO: call getLexemeUnit
//      getLexemeUnit()
//
//      // TODO: if token is NOT EOF
//      if (lexemeUnit.getToken() != Token.EOF) {
//
//        // TODO: if token is "*" or "/", add token as new branch and reset lexemeUnit;
//        //  then add result of "parseFactor" and "parseTermPrime" as new branches
//        if (lexemeUnit.getToken() == Token.MUL_OP) {
//          tree.add(new Tree(lexemeUnit.getLexeme()))
//          lexemeUnit = null // always set lexemeUnit to null after consuming it
//          tree.add(parseFactor())
//          tree.add(parseTermPrime())
//        }
//        // else means "epsilon" production
//      }
//
//      // TODO: return the tree
//      tree
//    }
//
//
//      // factor = identifier | int_literal
//      private def parseFactor(): Tree = {
//        // TODO: create a tree with label "factor"
//        val tree = new Tree("factor")
//
//        // TODO: call getLexemeUnit
//        getLexemeUnit()
//
//        // TODO: if token is NOT EOF
//        if (lexemeUnit.getToken() != Token.EOF) {
//          // TODO: if token is an identifier, add result of "parseIdentifier" as new branch and reset lexemeUnit
//          if (lexemeUnit.getToken() == Token.IDENTIFIER) {
//            tree.add(parseIdentifier())
//            lexemeUnit = null // always set lexemeUnit to null after consuming it
//          }
//          // TODO: if token is a Int_literal, add result of "parseLiteral" as new branch and reset lexemeUnit
//          else if (lexemeUnit.getToken() == Token.INT_LITERAL) {
//            tree.add(parse_intLiteral())
//            lexemeUnit = null // always set lexemeUnit to null after consuming it
//          }
//
//          // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//          else
//            throw new Exception("Syntax Analyzer Error: identifier or \"(int) literal\" expected!")
//        }
//        // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//        else
//          throw new Exception("Syntax Analyzer Error: identifier or \"(int) literal\" expected!")
//
//        // TODO: return the tree
//        tree
//      }
//
//
//        // literal = int_literal | bool_literal
//        private def parseLiteral(): Tree = {
//          // TODO: create a tree with label "literal"
//          val tree = new Tree("literal")
//
//          // TODO: call getLexemeUnit
//          getLexemeUnit()
//
//          // TODO: if token is NOT EOF
//          if (lexemeUnit.getToken() != Token.EOF) {
//            // TODO: if token is a Int_literal, add result of "parseLiteral" as new branch and reset lexemeUnit
//            if (lexemeUnit.getToken() == Token.INT_LITERAL) {
//              tree.add(parse_intLiteral())
//              lexemeUnit = null // always set lexemeUnit to null after consuming it
//            }
//            // TODO: if token is a Int_boolean, add result of "parseLiteral" as new branch and reset lexemeUnit
//            else if (lexemeUnit.getToken() == Token.TRUE || lexemeUnit.getToken() == Token.FALSE) {
//              tree.add(parse_bool_literal())
//              lexemeUnit = null // always set lexemeUnit to null after consuming it
//            }
//
//            // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//            else
//              throw new Exception("Syntax Analyzer Error: \"(int) literal\" or \"(bool) literal\" expected!")
//          }
//          // TODO: otherwise, throw an exception saying that "identifier, literal or opening parenthesis" was expected
//          else
//            throw new Exception("Syntax Analyzer Error: \"(int) literal\" or \"(bool) literal\" expected!")
//
//          // TODO: return the tree
//          tree
//        }
//
//
//        // int_literal = digit { digit }
//        // TODO: return a new tree with the label "literal" followed by the actual lexeme
//        private def parse_intLiteral() = new Tree("int_literal: '" + lexemeUnit.getLexeme() + "'")
//
//
//        // bool_literal = ´true´ | ´false´
//        private def parse_bool_literal(): Tree = {
//          // TODO: create a tree with label "expression'"
//          val tree = new Tree("bool_literal")
//
//          // TODO: call getLexemeUnit
//          getLexemeUnit()
//
//          // TODO: if token is NOT EOF
//          if (lexemeUnit.getToken() != Token.EOF) {
//            // TODO: if token is "TRUE" or "FALSE", add token as new branch and reset lexemeUnit;
//            //  then add result of "parseTerm" and "parseExpressionPrime" as new branches
//            if (lexemeUnit.getToken() == Token.TRUE || lexemeUnit.getToken() == Token.FALSE) {
//              tree.add(new Tree(lexemeUnit.getLexeme()))
//              lexemeUnit = null // always set lexemeUnit to null after consuming it
//
//            }
//            // else means "epsilon" production
//          }
//
//          // TODO: return the tree
//          tree
//        }
//        // bool_expr = bool_literal | arithm_expr ( ´>´ | ´>=´ | ´=´ | ´<=´ | ´<´ ) arithm_exp
//
//
//      }
//
//object SyntaxAnalyzer {
//  def main(args: Array[String]): Unit = {
//    // check if source file was passed through the command-line
//    if (args.length != 1) {
//      print("Missing source file!")
//      System.exit(1)
//    }
//
//    val syntaxAnalyzer = new SyntaxAnalyzer(args(0))
//    val parseTree = syntaxAnalyzer.parse()
//    print(parseTree)
//  }
//}
//