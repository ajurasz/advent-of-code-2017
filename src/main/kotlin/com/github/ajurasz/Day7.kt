package com.github.ajurasz

import java.util.regex.Pattern
import kotlin.math.absoluteValue

object Day7 {

    private val pattern: Pattern = Pattern.compile("""(\w+ )\((\d+)\)( -> )?(.*)?""")

    private data class Tree(val name: String, val weight: Int, var parent: Tree? = null, val nodes: MutableList<Tree> = mutableListOf()) {
        val isBalanced: Boolean by lazy { nodes.map { it.totalWeight }.distinct().size == 1 }
        val totalWeight: Int by lazy { weight + nodes.sumBy { it.totalWeight } }
        fun balanceTree(imbalance: Int? = null): Int =
            if (imbalance != null && isBalanced) {
                weight - imbalance
            } else {
                val subtreeGroups = nodes.groupBy { it.totalWeight }
                val badTree = subtreeGroups.minBy { it.value.size }?.value?.first()!!
                badTree.balanceTree(imbalance ?: subtreeGroups.keys.reduce { a, b -> a - b }.absoluteValue)
            }
    }

    private data class TreeWrapper(val tree: Tree, val disc: List<String>)

    private fun parseTree(input: String): Tree {
        fun parse(input: String) = input
                .split("\n")
                .map {
                    val matcher = pattern.matcher(it)
                    matcher.find()
                    TreeWrapper(
                            Tree(matcher.group(1).trim(), matcher.group(2).toInt()),
                            matcher?.group(4)?.split(",")?.map { it.trim() }?.filter { it.isNotBlank() } ?: emptyList()
                    )
                }

        val lookup = parse(input).associateBy { it.tree.name }
        val parentAndChild = mutableSetOf<Pair<Tree, String>>()

        lookup.values.forEach { parent ->
            parent.disc.forEach { parentAndChild.add(parent.tree to it) }
        }

        parentAndChild.forEach { (parent, child) ->
            with(lookup.getValue(child)) {
                parent.nodes.add(this.tree)
                this.tree.parent = parent
            }
        }

        return lookup.values.first { it.tree.parent == null }.tree
    }

    @JvmStatic
    fun part1(input: String) = parseTree(input).name

    @JvmStatic
    fun part2(input: String) = parseTree(input).balanceTree()

}