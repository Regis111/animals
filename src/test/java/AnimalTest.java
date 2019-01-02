import static org.junit.Assert.*;

import javafx.geometry.Pos;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AnimalTest {

    @Test
    public void generateArrayTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> klasa = Animal.class;
        Method metoda = klasa.getDeclaredMethod("generateArray");
        metoda.setAccessible(true);
        ArrayList<Integer> genes1 = new ArrayList<>(Arrays.asList(1,2,0,4,0,3,0,0));
        ArrayList<Integer> genes2 = new ArrayList<>(Arrays.asList(5,1,2,0,0,0,4,5));
        Animal animal1 = new Animal(genes1,0,new Position(2,2));
        Animal animal2 = new Animal(genes2,0,new Position(2,2));
        Integer[] array1 = (Integer[])metoda.invoke(animal1);
        Integer [] array2 = {0,1,1,3,3,3,3,5,5,5};
        Integer [] array3 = (Integer[]) metoda.invoke(animal2);
        Integer [] array4 = {0,0,0,0,0,1,2,2,6,6,6,6,7,7,7,7,7};
        assertTrue(Arrays.equals(array1,array2));
        assertTrue(Arrays.equals(array3,array4));
    }
}
