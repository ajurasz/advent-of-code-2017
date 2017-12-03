package com.github.ajurasz

import spock.lang.Specification

import static com.github.ajurasz.Day3.part1
import static com.github.ajurasz.Day3.part2

class Day3Spec extends Specification {

    def "should resolve part1"() {
        expect:
        part1(value) == result

        where:
        value   || result
        12      || 3
        13      || 4
        23      || 2
        19      || 2
        1024    || 31
        368078  || 371
    }
}
