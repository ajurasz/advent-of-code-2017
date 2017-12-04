package com.github.ajurasz

import spock.lang.Specification

import static com.github.ajurasz.Day4.part1
import static com.github.ajurasz.Day4.part2

class Day4Spec extends Specification {


    def "should resolve part1"() {
        expect:
        part1(input) == output

        where:
        input                                               || output
        "aa bb cc dd ee\naa bb cc dd aa\naa bb cc dd aaa"   || 2
        getClass().getResource("/day4.txt").text            || 386
    }

    def "should resolve part2"() {
        expect:
        part2(input) == output

        where:
        input                                                                                               || output
        "abcde fghij\nabcde xyz ecdab\na ab abc abd abf abj\niiii oiii ooii oooi oooo\noiii ioii iioi iiio" || 3
        getClass().getResource("/day4.txt").text                                                            || 208
    }
}
