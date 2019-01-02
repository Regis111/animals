import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Animal extends MapElement{
    private ArrayList<Integer> genes;

    public Animal(ArrayList<Integer> genes, int energy, Position position){
        super(position,energy);
        this.genes = genes;
    }
    public Animal(int energy, Position position){
        super(position,energy);
        ArrayList<Integer> genes = new ArrayList<>();
        genes.add(0, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(1, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(2, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(3, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(4, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(5, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(6, ThreadLocalRandom.current().nextInt(0,8));
        genes.add(7, ThreadLocalRandom.current().nextInt(0,8));
        this.genes = genes;
    }

    @Override
    public String toString(){
        return "A";
    }

    public Animal getChild(RectangularMap map){
        ArrayList<Integer> newGenes = genes;
        int randomIndex = ThreadLocalRandom.current().nextInt(0, 8);
        int addOrDecreaseOrNothing = ThreadLocalRandom.current().nextInt(0,  3);
        int value = newGenes.get(randomIndex);
        newGenes.remove(randomIndex);
        if(addOrDecreaseOrNothing == 2){
            newGenes.add(randomIndex,value + 1);
        }
        else if(addOrDecreaseOrNothing == 0 && value > 0){
            newGenes.add(randomIndex,value - 1);
        }
        else {
            newGenes.add(randomIndex,value);
        }
        Position childPosition = animalBeyondTheMap(getPosition().x + 1, getPosition().y + 1,map);
        Animal child =  new Animal(newGenes,this.getEnergy() / 2,childPosition);
        setEnergy(getEnergy()-getEnergy()/2);
        return child;
    }

    public void eat(RectangularMap map){
        Plant food = map.getPlants().get(getPosition());
        if(food != null){
            setEnergy(getEnergy() + food.getEnergy());
            System.out.println(toString() + " zjadł " + food.getEnergy() + " energii");
        }
        map.getPlants().remove(getPosition());
    }

    public void move(RectangularMap map){
        Integer array [] = generateArray();
        boolean canMoveTo = false;
        Position newPosition = null;
        int x0 = getPosition().x, y0 = getPosition().y;
        while(!canMoveTo){
            int randomNum = ThreadLocalRandom.current().nextInt(0, array.length);
            int direction = array[randomNum];
            MapDirection mapDirection = toMapDirectionFromInt(direction);
            int x1 = x0 + mapDirection.vector().x;
            int y1 = y0 + mapDirection.vector().y;
            newPosition = animalBeyondTheMap(x1,y1,map);
            if(map.canMoveTo(newPosition)){
                canMoveTo = true;
            }
        }
        setPosition(newPosition);
        map.positionChanged(new Position(x0,y0));
    }

    private Position animalBeyondTheMap(int x1,int y1 ,RectangularMap map){
        if(x1 == -1){
            x1 += map.getWidth();
        }
        else if(x1 == map.getWidth()){
            x1 += -map.getWidth();
        }
        if(y1 == -1){
            y1 += map.getHeight();
        }
        else if(y1 == map.getHeight()){
            y1 += -map.getHeight();
        }
        return new Position(x1,y1);
    }

    private MapDirection toMapDirectionFromInt(int number){
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
    public Integer [] generateArray(){
        int sum = genes.stream().mapToInt(Integer::intValue).sum();
        Integer [] array = new Integer[sum];
        int sumOfPreviousGenes = 0;
        for(int i = 0; i < genes.size() ; i++){
            int value = genes.get(i);
            for(int j = 0; j < value; j++){
                try{
                    array[j + sumOfPreviousGenes] = i;
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("error w generateArray, indeks to " + (int)(j + sumOfPreviousGenes) + ", a wielkość tablicy to" + sum);
                }

            }
            sumOfPreviousGenes += value;
        }
        return array;
    }
}