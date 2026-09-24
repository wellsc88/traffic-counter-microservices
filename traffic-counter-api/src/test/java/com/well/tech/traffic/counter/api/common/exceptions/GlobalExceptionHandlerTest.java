package com.well.tech.traffic.counter.api.common.exceptions;

import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    private static class TestBaseException extends BaseException {

        public TestBaseException(String message, int status) {
            super(message, status);
        }
    }

    @Test
    void shouldHandleBaseException() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/users");

        BaseException exception =
                new TestBaseException(
                        "User not found",
                        404
                );

        ResponseEntity<ApiError> response =
                handler.handleBaseException(
                        exception,
                        request
                );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().status())
                .isEqualTo(404);

        assertThat(response.getBody().error())
                .isEqualTo("Not Found");

        assertThat(response.getBody().message())
                .isEqualTo("User not found");

        assertThat(response.getBody().path())
                .isEqualTo("/api/users");

        verify(request)
                .getRequestURI();
    }

    @Test
    void shouldHandleBaseExceptionWithInvalidStatus() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/error");

        BaseException exception =
                new TestBaseException(
                        "Unexpected business error",
                        999
                );

        ResponseEntity<ApiError> response =
                handler.handleBaseException(
                        exception,
                        request
                );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().status())
                .isEqualTo(500);

        assertThat(response.getBody().error())
                .isEqualTo("Internal Server Error");

        assertThat(response.getBody().message())
                .isEqualTo("Unexpected business error");

        assertThat(response.getBody().path())
                .isEqualTo("/api/error");

        verify(request)
                .getRequestURI();
    }

    @Test
    void shouldHandleGenericException() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/error");

        ResponseEntity<ApiError> response =
                handler.handleGenericException(
                        new RuntimeException(),
                        request
                );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().status())
                .isEqualTo(500);

        assertThat(response.getBody().error())
                .isEqualTo("Internal Server Error");

        assertThat(response.getBody().message())
                .isEqualTo(
                        "Unexpected error occurred"
                );

        assertThat(response.getBody().path())
                .isEqualTo("/api/error");

        verify(request)
                .getRequestURI();
    }

    @Test
    void shouldHandleTypeMismatchException() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/users/test");

        MethodArgumentTypeMismatchException exception =
                new MethodArgumentTypeMismatchException(
                        "abc",
                        Long.class,
                        "id",
                        mock(MethodParameter.class),
                        new IllegalArgumentException()
                );

        ResponseEntity<ApiError> response =
                handler.handleTypeMismatch(
                        exception,
                        request
                );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().status())
                .isEqualTo(400);

        assertThat(response.getBody().error())
                .isEqualTo("Bad Request");

        assertThat(response.getBody().message())
                .isEqualTo(
                        "Invalid value 'abc' for parameter 'id'"
                );

        assertThat(response.getBody().path())
                .isEqualTo("/api/users/test");
    }

    @Test
    void shouldHandleValidationExceptionTypeMismatch() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/users");

        FieldError fieldError =
                spy(
                        new FieldError(
                                "user",
                                "age",
                                "invalid"
                        )
                );

        when(fieldError.getCode())
                .thenReturn("typeMismatch");

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(
                        new Object(),
                        "user"
                );

        bindingResult.addError(fieldError);

        MethodArgumentNotValidException exception =
                mock(MethodArgumentNotValidException.class);

        when(exception.getBindingResult())
                .thenReturn(bindingResult);

        ResponseEntity<ApiError> response =
                handler.handleValidationException(
                        exception,
                        request
                );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().message())
                .isEqualTo(
                        "Invalid value for parameter 'age'"
                );
    }

    @Test
    void shouldHandleValidationExceptionWithDefaultMessage() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/users");

        FieldError fieldError =
                new FieldError(
                        "user",
                        "name",
                        "Name must not be blank"
                );

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(
                        new Object(),
                        "user"
                );

        bindingResult.addError(fieldError);

        MethodArgumentNotValidException exception =
                mock(MethodArgumentNotValidException.class);

        when(exception.getBindingResult())
                .thenReturn(bindingResult);

        ResponseEntity<ApiError> response =
                handler.handleValidationException(
                        exception,
                        request
                );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().message())
                .isEqualTo(
                        "name: Name must not be blank"
                );
    }

    @Test
    void shouldHandleValidationExceptionWithoutErrors() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/users");

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(
                        new Object(),
                        "user"
                );

        MethodArgumentNotValidException exception =
                mock(MethodArgumentNotValidException.class);

        when(exception.getBindingResult())
                .thenReturn(bindingResult);

        ResponseEntity<ApiError> response =
                handler.handleValidationException(
                        exception,
                        request
                );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().message())
                .isEqualTo("Invalid request");
    }

    @Test
    void shouldHandleResourceNotFoundException() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/users/10");

        BaseException exception =
                new ResourceNotFoundException(
                        "User not found"
                );

        ResponseEntity<ApiError> response =
                handler.handleBaseException(
                        exception,
                        request
                );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().status())
                .isEqualTo(404);

        assertThat(response.getBody().message())
                .isEqualTo("User not found");

        assertThat(response.getBody().path())
                .isEqualTo("/api/users/10");

        verify(request)
                .getRequestURI();
    }
}