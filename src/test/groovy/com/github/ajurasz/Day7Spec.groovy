package com.github.ajurasz

import spock.lang.Specification

import static com.github.ajurasz.Day7.part1
import static com.github.ajurasz.Day7.part2

class Day7Spec extends Specification {


    def "should resolve part1"() {
        expect:
        part1(input) == output

        where:
        input                                       || output
        "pbga (66)\nfwft (72) -> pbga"              || "fwft"
        getClass().getResource("/day7.txt").text    || "eugwuhl"
    }

    def "should resolve part2"() {
        expect:
        part2(input) == output

        where:
        input                                       || output
        getClass().getResource("/day7.txt").text    || 420
    }
}
