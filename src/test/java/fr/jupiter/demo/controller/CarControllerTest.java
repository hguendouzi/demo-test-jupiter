package fr.jupiter.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jupiter.demo.entity.Car;
import fr.jupiter.demo.service.CarService;
import fr.jupiter.demo.utils.MockData;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CarService service;

    MockData mockData;

    protected JacksonTester<Car> jsonCar;

    @BeforeEach
    protected void setUp() {
     mockData=new MockData();
     JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("Test call operation allCars")
    void should_return_200_when_call_getAllCars() throws Exception {
        Mockito.when(service.getAllCars()).thenReturn(mockData.mockCars());
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/car/list")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.[0].brand", Matchers.is("Volkswagen")))
                        .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    @Test
    @DisplayName("Test call operation saveCar")
    void should_return_200_when_call_saveCars() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/car/save")
                        .content(jsonCar.write(mockData.mockCar()).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Test call operation findCarByName")
    void should_return_200_when_call_findCarByName() throws Exception {
        Mockito.when(service.findCarByName("Golf")).thenReturn(mockData.mockCar());
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/car/Golf")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand", Matchers.is("Volkswagen")))
                .andExpect(jsonPath("$.name", Matchers.is("Golf")))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Test call operation findCarByBrand")
    void should_return_200_when_call_findCarByBrand() throws Exception {
        Mockito.when(service.findCarsByBrand("Volkswagen")).thenReturn(mockData.mockCars());
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/car/brand/Volkswagen")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].brand", Matchers.is("Volkswagen")))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Test call operation delete car by name")
    void should_return_200_when_call_deleteByName() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.delete("/car/delete/Golf")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

}