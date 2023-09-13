package sqlschemaAnalyzer;
// Generated from sql.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sqlParser}.
 */
public interface sqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sqlParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(@NotNull sqlParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(@NotNull sqlParser.SchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#foreignKeyIndex}.
	 * @param ctx the parse tree
	 */
	void enterForeignKeyIndex(@NotNull sqlParser.ForeignKeyIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#foreignKeyIndex}.
	 * @param ctx the parse tree
	 */
	void exitForeignKeyIndex(@NotNull sqlParser.ForeignKeyIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#foreignKeyConstraint}.
	 * @param ctx the parse tree
	 */
	void enterForeignKeyConstraint(@NotNull sqlParser.ForeignKeyConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#foreignKeyConstraint}.
	 * @param ctx the parse tree
	 */
	void exitForeignKeyConstraint(@NotNull sqlParser.ForeignKeyConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#variableName}.
	 * @param ctx the parse tree
	 */
	void enterVariableName(@NotNull sqlParser.VariableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#variableName}.
	 * @param ctx the parse tree
	 */
	void exitVariableName(@NotNull sqlParser.VariableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#checkEquation}.
	 * @param ctx the parse tree
	 */
	void enterCheckEquation(@NotNull sqlParser.CheckEquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#checkEquation}.
	 * @param ctx the parse tree
	 */
	void exitCheckEquation(@NotNull sqlParser.CheckEquationContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#variableKeyword}.
	 * @param ctx the parse tree
	 */
	void enterVariableKeyword(@NotNull sqlParser.VariableKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#variableKeyword}.
	 * @param ctx the parse tree
	 */
	void exitVariableKeyword(@NotNull sqlParser.VariableKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#checkVariablePasser}.
	 * @param ctx the parse tree
	 */
	void enterCheckVariablePasser(@NotNull sqlParser.CheckVariablePasserContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#checkVariablePasser}.
	 * @param ctx the parse tree
	 */
	void exitCheckVariablePasser(@NotNull sqlParser.CheckVariablePasserContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#keywordPrimaryKey}.
	 * @param ctx the parse tree
	 */
	void enterKeywordPrimaryKey(@NotNull sqlParser.KeywordPrimaryKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#keywordPrimaryKey}.
	 * @param ctx the parse tree
	 */
	void exitKeywordPrimaryKey(@NotNull sqlParser.KeywordPrimaryKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#equationOperator}.
	 * @param ctx the parse tree
	 */
	void enterEquationOperator(@NotNull sqlParser.EquationOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#equationOperator}.
	 * @param ctx the parse tree
	 */
	void exitEquationOperator(@NotNull sqlParser.EquationOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#checkConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCheckConstraint(@NotNull sqlParser.CheckConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#checkConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCheckConstraint(@NotNull sqlParser.CheckConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(@NotNull sqlParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(@NotNull sqlParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#variableType}.
	 * @param ctx the parse tree
	 */
	void enterVariableType(@NotNull sqlParser.VariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#variableType}.
	 * @param ctx the parse tree
	 */
	void exitVariableType(@NotNull sqlParser.VariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#keywordNotNull}.
	 * @param ctx the parse tree
	 */
	void enterKeywordNotNull(@NotNull sqlParser.KeywordNotNullContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#keywordNotNull}.
	 * @param ctx the parse tree
	 */
	void exitKeywordNotNull(@NotNull sqlParser.KeywordNotNullContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#keywordUnique}.
	 * @param ctx the parse tree
	 */
	void enterKeywordUnique(@NotNull sqlParser.KeywordUniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#keywordUnique}.
	 * @param ctx the parse tree
	 */
	void exitKeywordUnique(@NotNull sqlParser.KeywordUniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(@NotNull sqlParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(@NotNull sqlParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#checkOperator}.
	 * @param ctx the parse tree
	 */
	void enterCheckOperator(@NotNull sqlParser.CheckOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#checkOperator}.
	 * @param ctx the parse tree
	 */
	void exitCheckOperator(@NotNull sqlParser.CheckOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#localIndex}.
	 * @param ctx the parse tree
	 */
	void enterLocalIndex(@NotNull sqlParser.LocalIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#localIndex}.
	 * @param ctx the parse tree
	 */
	void exitLocalIndex(@NotNull sqlParser.LocalIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#checkEquationL}.
	 * @param ctx the parse tree
	 */
	void enterCheckEquationL(@NotNull sqlParser.CheckEquationLContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#checkEquationL}.
	 * @param ctx the parse tree
	 */
	void exitCheckEquationL(@NotNull sqlParser.CheckEquationLContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#variableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterVariableConstraint(@NotNull sqlParser.VariableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#variableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitVariableConstraint(@NotNull sqlParser.VariableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#checkEquationR}.
	 * @param ctx the parse tree
	 */
	void enterCheckEquationR(@NotNull sqlParser.CheckEquationRContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#checkEquationR}.
	 * @param ctx the parse tree
	 */
	void exitCheckEquationR(@NotNull sqlParser.CheckEquationRContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#checkEquation2}.
	 * @param ctx the parse tree
	 */
	void enterCheckEquation2(@NotNull sqlParser.CheckEquation2Context ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#checkEquation2}.
	 * @param ctx the parse tree
	 */
	void exitCheckEquation2(@NotNull sqlParser.CheckEquation2Context ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(@NotNull sqlParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(@NotNull sqlParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#foreignKeyTable}.
	 * @param ctx the parse tree
	 */
	void enterForeignKeyTable(@NotNull sqlParser.ForeignKeyTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#foreignKeyTable}.
	 * @param ctx the parse tree
	 */
	void exitForeignKeyTable(@NotNull sqlParser.ForeignKeyTableContext ctx);
}