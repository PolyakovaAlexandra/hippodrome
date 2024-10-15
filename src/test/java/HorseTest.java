import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    void nullException(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1,1) );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "    " })
    void blankException(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 1,1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void negativeSpeedException(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Speedy", -1,1) );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    void negativeDistanceException(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Speedy", 1,-1) );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    void getName() {
        Horse horse = new Horse("Speedy", 1,1);
        assertEquals("Speedy",horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Speedy", 1,1);
        assertEquals(1,horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Speedy", 1,1);
        assertEquals(1,horse.getDistance());
    }
    @Test
    void getZeroDistance() {
        Horse horse = new Horse("Speedy", 1);
        assertEquals(0,horse.getDistance());
    }
    @Test
    public void invokeGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            new Horse("Speedy", 1).move();
            mockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource (doubles = {0, 0.2,0.5,0.9,1})
    public void checkValueOfFormula(double rand) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("Speedy", 1,1);
            mockedStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(rand);
            horse.move();
            assertEquals(1 + 1* rand,horse.getDistance());
        }
    }

}
