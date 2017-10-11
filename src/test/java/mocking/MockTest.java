package mocking;

import org.junit.Test;

import static mocking.Mock.mock;
import static mocking.Mock.verify;


public class MockTest {


    @Test
    public void shouldNotThrowExceptionWhenExpectationMet() {
        Collaborator collaborator = mock(Collaborator.class);

        collaborator.collaboratorMethod("some-args");

        verify(collaborator).collaboratorMethod("some-args");
    }

    @Test(expected = MockVerificationException.class)
    public void shouldThrowExceptionWhenNotCalled() {
        Collaborator collaborator = mock(Collaborator.class);

        verify(collaborator).collaboratorMethod("some-args");
    }

    @Test(expected = MockVerificationException.class)
    public void shouldThrowExceptionWhenArgsDiffer() {
        Collaborator collaborator = mock(Collaborator.class);

        collaborator.collaboratorMethod("some-args");

        verify(collaborator).collaboratorMethod("other-args");
    }

    @Test(expected = MockVerificationException.class)
    public void shouldThrowExceptionWhenOtherMethodCalled() {
        Collaborator collaborator = mock(Collaborator.class);

        collaborator.otherMethod("some-args");

        verify(collaborator).collaboratorMethod("other-args");
    }
}
