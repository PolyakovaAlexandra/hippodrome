import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    @Test
    void nullHorses(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null) );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    void blankHorses() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
           horses.add(new Horse(""+i, i, i)) ;
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
     void makeMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            horses.add(mock(Horse.class)) ;
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    void makeWinner(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse(""+i, i, i)) ;
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horses.get(29), hippodrome.getWinner()); ;
    }
}
