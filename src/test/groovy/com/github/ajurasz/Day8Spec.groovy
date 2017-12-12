package com.github.ajurasz

import spock.lang.Specification

import static com.github.ajurasz.Day8.part1
import static com.github.ajurasz.Day8.part2

class Day8Spec extends Specification {


    def "should resolve part1"() {
        expect:
        part1(input) == output

        where:
        input                                                                           || output
        "b inc 5 if a > 1\na inc 1 if b < 5\nc dec -10 if a >= 1\nc inc -20 if c == 10" || 1
        getClass().getResource("/day8.txt").text                                        || 5946
    }

    def "should resolve part2"() {
        expect:
        part2(input) == output

        where:
        input                                                                           || output
        "b inc 5 if a > 1\na inc 1 if b < 5\nc dec -10 if a >= 1\nc inc -20 if c == 10" || 10
        getClass().getResource("/day8.txt").text                                        || 6026
    }
}
