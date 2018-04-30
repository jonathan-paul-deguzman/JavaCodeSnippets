package com.jonathanpaul.javacodesnippets.test;

import com.jonathanpaul.javacodesnippets.java.Product;
import com.jonathanpaul.javacodesnippets.java.Shipment;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class ShipmentTest {

    private Shipment shipment = new Shipment();

    Product door;

    Product floorPanel;

    Product window;

    @Before
    public void setUp() {
        door = new Product("Wooden Door", 35);
        floorPanel = new Product("Floor Panel", 25);
        window = new Product("Glass Window", 10);
    }

    @Test
    public void shouldAddItems() {
        shipment.add(door);
        shipment.add(window);

        assertThat(shipment, contains(door, window));
    }

    @Test
    public void shouldReplaceItems() {
        shipment.add(door);
        shipment.add(window);

        shipment.replace(door, floorPanel);

        assertThat(shipment, contains(floorPanel, window));
    }

    // We originally included this test method to test a corner case of our replace method
    // Now that we have addressed this corner case in our code by checking if index is -1,
    // we no longer need this method. I guess one of the cool things about TDD is that we
    // can inspect corner cases early and then optimize our code as necessary.
    //@Test (expected = ArrayIndexOutOfBoundsException.class)
    public void shouldNotReplaceMissingItemsOLD() {
        shipment.add(window);

        shipment.replace(door, floorPanel);
    }

    @Test
    public void shouldNotReplaceMissingItems() {
        shipment.add(window);

        shipment.replace(door, floorPanel);

        assertThat(shipment, contains(window));
    }

    @Test
    public void shouldIdentifyVanRequirements() {
        shipment.add(door);
        shipment.add(window);
        shipment.add(floorPanel);

        shipment.prepare();

        assertThat(shipment.getLightVanProducts(), contains(window));
        // Note that the contains method will NOT return true if floorPanel and door are not in the correct order
        assertThat(shipment.getHeavyVanProducts(), contains(floorPanel, door));
    }
}
