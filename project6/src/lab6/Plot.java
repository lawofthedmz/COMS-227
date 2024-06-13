package lab6;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import plotter.Plotter;
import plotter.Polyline;

public class Plot {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Polyline> list = readFile("hello.txt");
        Plotter plotter = new Plotter();

        for (Polyline p : list) {
            plotter.plot(p);
        }
    }

    private static Polyline parseOneLine(String line) {
        if (line.trim().isEmpty() || line.trim().startsWith("#")) {
            return null; // Ignore comments and blank lines
        }
        
        Scanner scanner = new Scanner(line);
        
        int width = 1; // Default width is 1 pixel
        if (scanner.hasNextInt()) {
            width = scanner.nextInt();
        }
        
        String color = scanner.next();
        Polyline polyline = new Polyline(color, width);
        
        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            polyline.addPoint(new Point(x, y));
        }
        
        return polyline;
    }

    private static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException {
        ArrayList<Polyline> polylines = new ArrayList<>();

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Polyline polyline = parseOneLine(line);
            if (polyline != null) {
                polylines.add(polyline);
            }
        }
        
        scanner.close();
        return polylines;
    }
}
