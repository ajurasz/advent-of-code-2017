package com.github.ajurasz

import spock.lang.Specification

import static com.github.ajurasz.Day1.captchaV1
import static com.github.ajurasz.Day1.captchaV2

class Day1Spec extends Specification {


    def "should generate captcha v1"() {
        expect:
        captchaV1(input) == output

        where:
        input       || output
        "1122"      || 3
        "1111"      || 4
        "1234"      || 0
        "91212129"  || 9
    }

    def "captcha v1 should pass puzzle challenge"() {
        when:
        def result = captchaV1(getClass().getResource("/day1.txt").text)

        then:
        result == 1069
    }

    def "should generate captcha v2"() {
        expect:
        captchaV2(input) == output

        where:
        input       || output
        "1212"      || 6
        "1221"      || 0
        "123425"    || 4
        "123123"    || 12
        "12131415"  || 4
    }

    def "captcha v2 should pass puzzle challenge"() {
        when:
        def result = captchaV2(getClass().getResource("/day1.txt").text)

        then:
        result == 1268
    }
}
