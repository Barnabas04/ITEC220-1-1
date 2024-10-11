import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPo = 0;
        for (Point currPt : s.getPoints()) {
            numPo = numPo + 1;
        }
        return numPo;
    }

    public double getAverageLength(Shape s) {
        double allSumLen = getPerimeter(s);
        double numSides = getNumPoints(s);
        double avergLen = allSumLen / numSides;
        return avergLen;
    }

    public double getLargestSide(Shape s) {
        double largestDist = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) { 
            double currDist = prevPt.distance(currPt);
            if (largestDist < currDist) {
                largestDist = currDist;
            }
        }
        return largestDist;
    }

    public double getLargestX(Shape s) {
        double largstX = 0.0;
        for (Point currPt : s.getPoints()) {
            int currX = currPt.getX();
            if (largstX < currX) {
                largstX = currX;
            } 
        }
        return largstX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (largestPerim < length) {
                largestPerim = length;
            }
        }
        return largestPerim;
    }

    public File getFileWithLargestPerimeter() {
        double largestPerim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        //File fileName = new File("");
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            File fileName = new File(f.getName()); 
            
            if (largestPerim < length) {
                largestPerim = length;
                temp = fileName;
            }
        }
        return temp;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int points = getNumPoints(s);
        System.out.println("number of points = " + points);
        
        double average = getAverageLength(s);
        System.out.println("average length = " + average);
        
        double longest = getLargestSide(s);
        System.out.println("Largest side = " + longest);
        
        double larstX = getLargestX(s);
        System.out.println("largest X = " + larstX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimOfFiles = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter of all files = " + largestPerimOfFiles);
    }

    public void testFileWithLargestPerimeter() {
        File FileName = getFileWithLargestPerimeter();
        System.out.println(FileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
