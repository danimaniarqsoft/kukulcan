/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.engine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.kukulkan.templating.domain.Room;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;
import mx.infotec.dads.kukulkan.util.BasePathEnum;

/**
 * Test for GeneratorService
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class FiwareOrionDataTest {

    @Autowired
    private TemplateService templateService;

    @Test
    public void generationService() throws Exception {
        List<Room> rooms = new ArrayList<>();
        Map<String, Object> input = new HashMap<String, Object>();

        int order = 1;
        for (int i = 1; i < 10; i++) {
            Random random = new Random();
            Room room = new Room();
            room.setEntityType("Room");
            room.setCategory("GeneralRoom");
            room.setOrder(order++);
            room.setCount(i);
            room.setId(room.getId() + "-" + room.getCount());
            room.setPressureValue(random.nextInt(79 - 10 + 1) + 10);
            room.setTemperatureValue(random.nextInt(79 - 10 + 1) + 10);
            rooms.add(room);
        }
        for (int i = 1; i < 30; i++) {
            Random random = new Random();
            Room room = new Room();
            room.setEntityType("Car");
            room.setCategory("CarShop");
            room.setOrder(order++);
            room.setCount(i);
            room.setId(room.getId() + "-" + room.getCount());
            room.setPressureValue(random.nextInt(79 - 10 + 1) + 10);
            room.setTemperatureValue(random.nextInt(79 - 10 + 1) + 10);
            rooms.add(room);
        }
        for (int i = 1; i < 47; i++) {
            Random random = new Random();
            Room room = new Room();
            room.setEntityType("Kitchen");
            room.setCategory("HouseRoom");
            room.setOrder(order++);
            room.setCount(i);
            room.setId(room.getId() + "-" + room.getCount());
            room.setPressureValue(random.nextInt(79 - 10 + 1) + 10);
            room.setTemperatureValue(random.nextInt(79 - 10 + 1) + 10);
            rooms.add(room);
        }
        for (int i = 1; i < 36; i++) {
            Random random = new Random();
            Room room = new Room();
            room.setEntityType("Bathroom");
            room.setCategory("HouseRoom");
            room.setOrder(order++);
            room.setCount(i);
            room.setId(room.getId() + "-" + room.getCount());
            room.setPressureValue(random.nextInt(79 - 10 + 1) + 10);
            room.setTemperatureValue(random.nextInt(79 - 10 + 1) + 10);
            rooms.add(room);
        }
        for (int i = 1; i < 24; i++) {
            Random random = new Random();
            Room room = new Room();
            room.setEntityType("Garage");
            room.setCategory("HouseRoom");
            room.setOrder(order++);
            room.setCount(i);
            room.setId(room.getId() + "-" + room.getCount());
            room.setPressureValue(random.nextInt(79 - 10 + 1) + 10);
            room.setTemperatureValue(random.nextInt(79 - 10 + 1) + 10);
            rooms.add(room);
        }
        for (int i = 1; i < 12; i++) {
            Random random = new Random();
            Room room = new Room();
            room.setEntityType("LivingRoom");
            room.setCategory("HouseRoom");
            room.setOrder(order++);
            room.setCount(i);
            room.setId(room.getId() + "-" + room.getCount());
            room.setPressureValue(random.nextInt(79 - 10 + 1) + 10);
            room.setTemperatureValue(random.nextInt(79 - 10 + 1) + 10);
            rooms.add(room);
        }
        for (int i = 1; i < 7; i++) {
            Random random = new Random();
            Room room = new Room();
            room.setEntityType("Church");
            room.setCategory("ReligionRoom");
            room.setOrder(order++);
            room.setCount(i);
            room.setId(room.getId() + "-" + room.getCount());
            room.setPressureValue(random.nextInt(79 - 10 + 1) + 10);
            room.setTemperatureValue(random.nextInt(79 - 10 + 1) + 10);
            rooms.add(room);
        }
        input.put("rooms", rooms);
        templateService.fillModel("orion", "fiware-orion-data/room.ftl", input, BasePathEnum.SRC_MAIN_JAVA, "room.js");
    }

}