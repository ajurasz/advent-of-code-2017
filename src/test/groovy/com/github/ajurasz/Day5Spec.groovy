package com.github.ajurasz

import spock.lang.Specification

import static com.github.ajurasz.Day5.part1
import static com.github.ajurasz.Day5.part2

class Day5Spec extends Specification {


    def "should resolve part1"() {
        expect:
        part1(input) == output

        where:
        input                                       || output
        "0\n3\n0\n1\n-3"                            || 5
        getClass().getResource("/day5.txt").text    || 354121
    }

    def "should resolve part2"() {
        expect:
        part2(input) == output

        where:
        input                                       || output
        "0\n3\n0\n1\n-3"                            || 10
        getClass().getResource("/day5.txt").text    || 27283023
    }
}
