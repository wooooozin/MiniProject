import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

/*
박우진 - Mission02. 나와 가까운 좌표값 찾기
*/

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return x == other.x && y == other.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}


public class Mission02 {
    private static boolean containsCoordinates(HashSet<Coordinate> set, Coordinate coordinate) {
        return set.contains(coordinate);
    }

    private static Coordinate findClosedCoordinate(HashSet<Coordinate> set, Coordinate myCoordinate) {
        Coordinate result = myCoordinate;
        double closestDistance = Double.MAX_VALUE;
        for (Coordinate coordinate : set) {
            double distance = Math.pow(coordinate.x - myCoordinate.x, 2) + Math.pow(coordinate.y - myCoordinate.y, 2);
            if (closestDistance > distance) {
                closestDistance = distance;
                result = coordinate;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Coordinate> set = new HashSet<>();

        System.out.println("나의 좌표를 입력해주세요:");
        System.out.print("나의 좌표 x 입력: ");
        int myPositionX = sc.nextInt();
        System.out.print("나의 좌표 y 입력: ");
        int myPositionY = sc.nextInt();
        Coordinate myCoordinate = new Coordinate(myPositionX, myPositionY);

        System.out.println("임의의 좌표를 입력해주세요.");
        while (set.size() < 10) {
            System.out.printf("임의의 좌표 %d번째 x 입력: ", set.size() + 1);
            int randomX = sc.nextInt();
            System.out.printf("임의의 좌표 %d번째 y 입력: ", set.size() + 1);
            int randomY = sc.nextInt();
            Coordinate randomCoordinate = new Coordinate(randomX, randomY);

            if (containsCoordinates(set, randomCoordinate)) {
                System.out.println("동일한 좌표가 이미 존재합니다. 새로운 값을 입력해주세요.");
            } else {
                set.add(new Coordinate(randomX, randomY));
            }
        }

        Coordinate result = findClosedCoordinate(set, myCoordinate);
        System.out.println("나의 좌표: " + myCoordinate);
        System.out.println("나의 좌표와 가장 가까운 좌표: " + result);
    }
}