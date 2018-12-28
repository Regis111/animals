import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Animal extends MapElement{
    private ArrayList<Integer> genes;

    public Animal(ArrayList<Integer> genes, int energy, Position position){
        super(position,energy);
        this.genes = genes;
    }
    public String toString(){
        return "M";
    }

    public Animal getChild(){
        ArrayList<Integer> newGenes = genes;
        int randomIndex = ThreadLocalRandom.current().nextInt(0, 8);
        int addOrDecreaseOrNothing = ThreadLocalRandom.current().nextInt(0,  3);
        int value = newGenes.get(randomIndex);
        newGenes.remove(randomIndex);
        if(addOrDecreaseOrNothing == 2){
            newGenes.add(randomIndex,value + 1);
        }
        else if(addOrDecreaseOrNothing == 0){
            newGenes.add(randomIndex,value - 1);
        }
        return new Animal(newGenes,this.getEnergy() / 2,getPosition());
    }

    public void eat(RectangularMap map){
        Plant food = map.getPlants().get(getPosition());
        if(food != null){
            setEnergy(getEnergy() + food.getEnergy());
        }
    }

    public void move(RectangularMap map){
        int array [] = generateArray();
        Position vector;
        boolean canMoveTo = false;
        int x0 = getPosition().x;
        int y0 = getPosition().y;
        int x1 = 0;
        int y1 = 0;
        while(!canMoveTo){
            int randomNum = ThreadLocalRandom.current().nextInt(0, array.length + 1);
            int direction = array[randomNum - 1];
            MapDirection mapDirection = toMapDirectionFromString(direction);
            vector = changeVector(mapDirection);
            x1 = x0 + vector.x;
            y1 = y0 + vector.y;
            if(x0 == -1){
                x1 += map.getWidth();
            }
            else if(x0 == map.getWidth()){
                x1 += -map.getWidth();
            }
            if(y0 == -1){
                y1 += map.getHeight();
            }
            else if(y0 == map.getHeight()){
                y1 += -map.getHeight();
            }
            if(map.canMoveTo(new Position(x1,y1))){
                canMoveTo = true;
            }
        }
        setPosition(new Position(x1,y1));
        map.positionChanged(new Position(x0,y0));
    }
    public Position changeVector(MapDirection direction){
        int x = 0;
        int y = 0;
        switch (direction){
            case SOUTH_WEST:
                x = -1;
                y = -1;
                break;
            case SOUTH_EAST:
                x = +1;
                y = -1;
                break;
            case NORTH_WEST:
                x = -1;
                y = +1;
                break;
            case NORTH_EAST:
                x = +1;
                y = +1;
                break;
            case NORTH:
                y = +1;
                break;
            case SOUTH:
                y = -1;
                break;
            case WEST:
                x = -1;
                break;
            case EAST:
                x = +1;
                break;
        }
        return new Position(x,y);
    }
    private MapDirection toMapDirectionFromString(int number){
        switch (number){
            case 0:
                return MapDirection.NORTH;
            case 1:
                return MapDirection.NORTH_WEST;
            case 2:
                return MapDirection.WEST;
            case 3:
                return MapDirection.SOUTH_WEST;
            case 4:
                return MapDirection.SOUTH;
            case 5:
                return MapDirection.SOUTH_EAST;
            case 6:
                return MapDirection.EAST;
            case 7:
                return MapDirection.NORTH_WEST;
            default:
                return null;
        }
    }
    /*
    generates array of int-s in range of 0-7 in order to take random number from it
     */
    private int [] generateArray(){
        int sum = genes.stream().mapToInt(Integer::intValue).sum();
        int [] array = new int [sum];
        int sumOfPreviousGenes = 0;
        for(int i = 0; i < genes.size() ; i++){
            int value = genes.get(i);
            for(int j = 0; j < value; j++){
                array[j + sumOfPreviousGenes] = value;
            }
            sumOfPreviousGenes += value;
        }
        return array;
    }
}