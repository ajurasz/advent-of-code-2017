package com.github.ajurasz

import spock.lang.Specification

import static com.github.ajurasz.Day2.checksumV1
import static com.github.ajurasz.Day2.checksumV2

class Day2Spec extends Specification {

    def "should generate checksumV1"() {
        given:
        def input = """ |5\t1\t9\t5
                        |7\t5\t3
                        |2\t4\t6\t8""".stripMargin()
        when:
        def result = checksumV1(input)
        then:
        result == 18
    }

    def "checksumV1 should pass puzzle challenge"() {
        given:
        def input = getClass().getResource("/day2.txt").text
        when:
        def result = checksumV1(input)
        then:
        result == 46402
    }

    def "should generate checksumV2"() {
        given:
        def input = """ |5\t9\t2\t8
                        |9\t4\t7\t3
                        |3\t8\t6\t5""".stripMargin()
        when:
        def result = checksumV2(input)
        then:
        result == 9
    }

    def "checksumV2 should pass puzzle challenge"() {
        given:
            def input = getClass().getResource("/day2.txt").text
        when:
            def result = checksumV2(input)
        then:
            result == 265
    }
}
