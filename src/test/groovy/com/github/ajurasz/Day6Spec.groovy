package com.github.ajurasz

import spock.lang.Specification

import static com.github.ajurasz.Day6.part1
import static com.github.ajurasz.Day6.part2

class Day6Spec extends Specification {


    def "should resolve part1"() {
        expect:
        part1(input) == output

        where:
        input                                       || output
        "0\t2\t7\t0"                                || 5
        getClass().getResource("/day6.txt").text    || 5042
    }

    def "should resolve part2"() {
        expect:
        part2(input) == output

        where:
        input                                       || output
        "0\t2\t7\t0"                                || 4
        getClass().getResource("/day6.txt").text    || 1086
    }
}
