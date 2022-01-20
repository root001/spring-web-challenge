package com.challenge.abdulbasit.spring.service;

import com.challenge.abdulbasit.spring.service.config.WebClientImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.*;

//sets order of test method run
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//so setup and clean up methods dont have to be declared static
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrainingTests {

    WebClientImpl webClientImpl = new WebClientImpl();
    private ChallengeService challengeService = new ChallengeService(webClientImpl);

    private static int no = 1;

    //lifecycle methods for setup and clean up
    @BeforeAll
    void beforeAll(){
        System.out.println("-- beforeAll Tasks e.g setup method to ....");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("-- beforeEach Tasks e.g loading browser instance/ data....");
    }

    @AfterAll
    void afterAll(){
        System.out.println("-- AfterAll Tasks e.g clean up method to ....");
    }

    @AfterEach
    void afterEach(){
        System.out.println("-- AfterEach Tasks e.g clean up for before Each....");
    }

    @Test
    @Disabled(value = "Disabled for some reason")
    void testOne(){
        System.out.println("Test 1 running ....");
    }

    @Test
    @DisabledIfSystemProperty(named = "env", matches = "staging", disabledReason = "Disabled for some environments -- e.g dev")
    void testOneDisabled(){
        System.out.println("Test 1 running ....");
    }

    @Test
    @DisabledIf(value = "provider", disabledReason = "Disabled because run is restricted to particular days")
    void testOneDisabledProvider(){
        System.out.println("Test 1 for disable running ....");
    }

    boolean provider(){
        return LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.SATURDAY);
    }

    @DisplayName("Testing with parameterized tests.")
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 10})
    void testTwo(int number){
        System.out.println("Test 2 running ....");
        assertTrue(challengeService.isEven(number));
    }

    @Order(1)
    @DisplayName("Testing with parameterized tests.")
    @ParameterizedTest(name = "Running - {index} with value: {arguments}")
    @ValueSource(ints = {1, 7, 9})
    void testFalse(int number){
        System.out.println("Test 2x running ....");
        assertFalse(challengeService.isEven(number));
    }

    @ParameterizedTest
 //   @EmptySource
    @NullAndEmptySource
 //   @NullSource
    @ValueSource(strings = {"abis", "inject"})
    void testThreeString(String input){
        System.out.println("Test string running ...."+ input);
    }

    @ParameterizedTest
    @CsvSource(value  = {"6, true", "5, false", "0, false"})
    @DisplayName("ParameterizedTest... custom asserts.")
    void testThree(int number, boolean assertion){
        assumeTrue(number > 0, "Test Case did not meet req criteria"); // would only run test case where the assumption holds
        System.out.println("Test 3 running for...."+number);
        Assertions.assertEquals(challengeService.isEven(number), assertion);
    }

    @ParameterizedTest
    @CsvSource(value  = {"38, true", "21, false", "7, false"})
    @DisplayName("ParameterizedTest... custom asserts.")
    void testThreeWithAssumptions(int number, boolean assertion){
        assumingThat(number > 10, () -> {
            System.out.println("Performing some actions on this.");
        }); // would only run test case where the assumption holds
        System.out.println("Test 3 running for...."+number);
        Assertions.assertEquals(challengeService.isEven(number), assertion);
    }

    @ParameterizedTest
    @CsvSource(value  = {"this istr?ue", "and?false"}, delimiter = '?')
    void testThreeString(String param1, String param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    @CsvFileSource(files  = "src/main/resources/TestFile.csv", numLinesToSkip = 1 , delimiter = '?') // having this an array files = {} or single file with delimiter or delimiterString
    void testStringCsvFile(String param1, String param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    @MethodSource(value = "stringSource")
    void methodSource(String param1){
        System.out.println("param1 = " + param1);
    }

    // method stringSource does not have to be declared as static because the @TestInstance(TestInstance.Lifecycle.PER_CLASS) is specified
    List<String> stringSource(){
        return Arrays.asList("xya", "cabs");
    }

    @ParameterizedTest
    //@MethodSource(value = "<package>.<class>#<sourceMethod>") ii if source method is in another class
    @MethodSource(value = "stringArgumentSource")
    void methodArgumentSource(String param1, double param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    List<Arguments> stringArgumentSource(){
        // some processor action
        return Arrays.asList(Arguments.arguments("tomatoe", 2.0), Arguments.arguments("taples", 27.2),
                Arguments.arguments("troves", 4.8));
    }

    // the MethodSource can take a stream or list argument.
    Stream<Arguments> strstringArgumentSource(){
        return Stream.of(Arguments.arguments("grapes", 5.0), Arguments.arguments("apples", 12.02) );
    }

    @RepeatedTest(3)
    void repeatingTest(RepetitionInfo repetitionInfo){
        System.out.println("repeating this test x : "+ ++no);
        Assumptions.assumingThat(repetitionInfo.getCurrentRepetition() == 2, () -> {
            System.out.println("Running on edge Cases for case :" + no);
        } );
    }

    @RepeatedTest(value = 4, name = "repititon of : {currentRepetition} and Total: {totalRepetitions}")
    @DisplayName("Repaeats")
    void customRepeatingTest(){
        System.out.println("repeating this test x : "+ no++);
    }

}
