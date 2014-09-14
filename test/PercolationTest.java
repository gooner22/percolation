import org.junit.Assert;
import org.junit.Test;

public class PercolationTest {



    @Test(expected = IllegalArgumentException.class)
    public void testPercolationShouldThrowIllegalArgumentExceptionIfNIsZero() throws Exception {
        new Percolation(0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPercolationShouldThrowIllegalArgumentExceptionIfNLessThenZero() throws Exception {
        new Percolation(0);
    }

    /**
     * isFull
     * @throws Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds0() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.isFull(0,0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds1() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.isFull(1,0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds3() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.isFull(1,11);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds4() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.isFull(11,1);
    }

    /**
     * isOpen
     * @throws Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds5() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.isOpen(0,0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds6() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.isOpen(1,0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds7() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.isOpen(1,11);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds8() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.isOpen(11,1);
    }

    /**
     * open
     * @throws Exception
     */

    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds9() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.open(0, 0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds10() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.open(1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds11() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.open(1, 11);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidationShouldThrowIndexOutOfBounds12() throws Exception {
        Percolation percolation = getPercolation10x10();
        percolation.open(11, 1);
    }


    @Test
    public void testCheckSiteIsNotOpenedByDefault() throws Exception {
        Percolation percolation = getPercolation10x10();
        Assert.assertFalse(percolation.isOpen(1, 1));
    }

    @Test
    public void testCheckSiteIsNotOpenedByDefault1() throws Exception {
        Percolation percolation = getPercolation10x10();
        Assert.assertFalse(percolation.isOpen(10, 10));
    }

    private Percolation getPercolation10x10() {
        return new Percolation(10);
    }
    private Percolation getPercolation4x4() {
        return new Percolation(4);
    }

    @Test
    public void testOpenTopNode() throws Exception {
        final Percolation percolation = getPercolation4x4();
        final int i = 1;
        final int j = 1;
        percolation.open(i, j);
        Assert.assertTrue(percolation.isOpen(i, j));
        Assert.assertTrue(percolation.isFull(i, j));
    }
    @Test
    public void testOpenBottomNode() throws Exception {
        final Percolation percolation = getPercolation4x4();
        final int i = 4;
        final int j = 1;
        percolation.open(i, j);
        Assert.assertTrue(percolation.isOpen(i,j));
        Assert.assertFalse(percolation.isFull(i, j));
    }

    @Test
    public void testOpenNodeFromFirstAndFromSecondRowBelow() throws Exception {
        final Percolation percolation = getPercolation4x4();
        final int i = 1;
        final int j = 1;
        percolation.open(i, j);
        percolation.open(i+1, j);
        Assert.assertTrue(percolation.isOpen(i, j));
        Assert.assertTrue(percolation.isOpen(i + 1, j));
        Assert.assertTrue(percolation.isFull(i, j));
        Assert.assertTrue(percolation.isFull(i+1,j));
    }
    @Test
    public void testOpenNodeFromFirstAndFromSecondRowBelowAndFromSecondRowRight() throws Exception {
        final Percolation percolation = getPercolation4x4();
        final int i = 1;
        final int j = 1;
        percolation.open(i, j);
        percolation.open(i+1, j);
        percolation.open(i+1, j+1);
        Assert.assertTrue(percolation.isOpen(i, j));
        Assert.assertTrue(percolation.isOpen(i + 1, j));
        Assert.assertTrue(percolation.isOpen(i+1,j+1));
        Assert.assertTrue(percolation.isFull(i, j));
        Assert.assertTrue(percolation.isFull(i+1,j));
        Assert.assertTrue(percolation.isFull(i+1,j+1));
    }
    @Test
    public void testOpenNodeFromFirstAndFromSecondRowBelowAndFromSecondRowLeft() throws Exception {
        final Percolation percolation = getPercolation4x4();
        final int i = 1;
        final int j = 2;
        percolation.open(i, j);
        percolation.open(i+1, j);
        percolation.open(i+1, j-1);
        Assert.assertTrue(percolation.isOpen(i,j));
        Assert.assertTrue(percolation.isOpen(i+1,j));
        Assert.assertTrue(percolation.isOpen(i+1,j-1));
        Assert.assertTrue(percolation.isFull(i,j));
        Assert.assertTrue(percolation.isFull(i+1,j));
        Assert.assertTrue(percolation.isFull(i+1,j-1));
    }

    @Test
    public void testPercolates() throws Exception {

        final Percolation percolation = getPercolation4x4();

        percolation.open(1,1);
        Assert.assertFalse(percolation.percolates());
        percolation.open(2,1);
        Assert.assertFalse(percolation.percolates());
        percolation.open(3,1);
        Assert.assertFalse(percolation.percolates());
        percolation.open(4,1);
        Assert.assertTrue(percolation.percolates());

    }
}