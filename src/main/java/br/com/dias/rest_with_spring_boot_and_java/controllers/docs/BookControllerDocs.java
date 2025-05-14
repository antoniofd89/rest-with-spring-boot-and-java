package br.com.dias.rest_with_spring_boot_and_java.controllers.docs;

import br.com.dias.rest_with_spring_boot_and_java.dto.v1.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookControllerDocs {
    @Operation(summary = "Find All Books",
            description = "Finds All Books",
            tags = {"books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                    )),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorize", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    List<BookDTO> findAll();

    @Operation(summary = "Finds a Books",
            description = "Find a specific book by your ID ",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200", content = @Content(
                            schema = @Schema(implementation = BookDTO.class)
                    )),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorize", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    BookDTO findById(@PathVariable("id") Long id);

    @Operation(summary = "Create a Book",
            description = "Create a Book ",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200", content = @Content(
                            schema = @Schema(implementation = BookDTO.class)
                    )),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorize", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    BookDTO create(@RequestBody BookDTO book);

    @Operation(summary = "Update a Book",
            description = "Update a Book ",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200", content = @Content(
                            schema = @Schema(implementation = BookDTO.class)
                    )),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorize", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    BookDTO update(@RequestBody BookDTO book);

    @Operation(summary = "Delete a Book",
            description = "Delete a specific book by their ID ",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200", content = @Content(
                            schema = @Schema(implementation = BookDTO.class)
                    )),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorize", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    ResponseEntity<?> delete(@PathVariable("id") Long id);
}
