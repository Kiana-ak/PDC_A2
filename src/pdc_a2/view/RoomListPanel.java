/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2.view;

import pdc_a2.model.RoomDatabase;
import pdc_a2.model.Customer;
import pdc_a2.model.Person;
import pdc_a2.model.Room;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.*;
import javax.swing.JPanel;
import pdc_a2.controller.HotelModel;

/**
 *
 * @author 64210 Displays list of available rooms when user selects a room,
 * prompts for their name to confirm booking
 */
public class RoomListPanel extends JPanel {

    private JList<String> roomList;
    private JButton bookButton;
    private HotelView view;
    private int bedCount;
    private List<Room> rooms;
    private String guestName;

    public RoomListPanel(List<Room> rooms, int bedCount, String guestName, HotelView view) {
        this.rooms = rooms;
        this.view = view;
        this.bedCount = bedCount;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        add(initRoomListPanel(), BorderLayout.WEST);
        add(initImagePanel(), BorderLayout.CENTER);
        add(initBottomPanel(), BorderLayout.SOUTH);
    }

    //scrollable room list
    private JScrollPane initRoomListPanel() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Room room : rooms) {
            String status = room.isBooked() ? "(Booked)" : "";
            listModel.addElement("Room " + room.getRoomNumber() + " " + status);
        }

        // Enable book button when a room is selected
        roomList = new JList<>(listModel);
        roomList.addListSelectionListener(e -> {
            bookButton.setEnabled(roomList.getSelectedIndex() != -1);
        });

        roomList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Available Rooms"));
        scrollPane.setPreferredSize(new Dimension(200, 300));

        return scrollPane;
    }

    //Show an image of the selected room type
    private JPanel initImagePanel() {
        JLabel imageLabel;
        try {
            Image img = new ImageIcon(getClass().getResource("/resources/room" + bedCount + ".png")).getImage();
            Image scaledImg = img.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaledImg));
        } catch (Exception e) {
            imageLabel = new JLabel("Image not found");
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(imageLabel, BorderLayout.CENTER);
        return panel;
    }

    //booking button panel and action listeners
    private JPanel initBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bookButton = new JButton("Book Selected Room");
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        bookButton.setEnabled(false); // enable only when a room is selected

        bookButton.addActionListener(e -> handleBooking());
        bottomPanel.add(bookButton);
        bottomPanel.add(createBackButton());

        return bottomPanel;
    }

    //Handle the booking process when user clicks Book
    private void handleBooking() {
        String selected = roomList.getSelectedValue();
        if (selected != null) {
            int roomNumber = Integer.parseInt(selected.split(" ")[1]);
            String guestName = promptForGuestName(roomNumber);

            if (guestName != null) {
                guestName = guestName.trim();

                if (!isValidGuestName(guestName)) {
                    JOptionPane.showMessageDialog(this, "Name must contain only letters and spaces.");
                    return;
                }

                this.guestName = guestName;
                Room bookedRoom = new Room(roomNumber, bedCount);
                Person guest = new Customer(guestName);

                updateDatabase(roomNumber, guestName);
                showInvoicePreview(guest, bookedRoom);
                refreshRoomList(guest.getName());
            } else {
                JOptionPane.showMessageDialog(this, "Booking cancelled.");
            }
        }
    }

    public static boolean isValidGuestName(String name) {
        return name != null && name.trim().matches("[a-zA-Z ]+");
    }

    //Asks the user to input their name to confirm booking
    private String promptForGuestName(int roomNumber) {
        return JOptionPane.showInputDialog(
                this,
                "Enter guest name to confirm booking for Room " + roomNumber + ":",
                "Booking Confirmation",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    // Update the room in the database to set BOOKED = TRUE
    private void updateDatabase(int roomNumber, String guestName) {
        RoomDatabase roomDatabase = new RoomDatabase();
        roomDatabase.bookRoom(roomNumber, guestName);
    }

    //Shows the invoice in a preview dialog and lets the user save it
    private void showInvoicePreview(Person guest, Room room) {
        String invoiceText = "Hotel Booking Invoice\n"
                + "----------------------\n"
                + "Customer: " + guest.getName() + "\n"
                + "Role: " + guest.getRole() + "\n"
                + "Room Number: " + room.getRoomNumber() + "\n"
                + "Beds: " + room.getBeds() + "\n"
                + "Price: $" + room.getPrice() + "\n\n"
                + "Thank you for booking with us!";

        JTextArea invoiceArea = new JTextArea(invoiceText);
        invoiceArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        invoiceArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(invoiceArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JButton saveButton = new JButton("Save Invoice");

        saveButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Invoice");
            fileChooser.setSelectedFile(new java.io.File("invoice_" + guest.getName().replaceAll(" ", "_") + ".txt"));

            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    writer.print(invoiceText);
                    JOptionPane.showMessageDialog(this, "Invoice saved successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving invoice: " + ex.getMessage());
                }
            }
        });

        JPanel previewPanel = new JPanel(new BorderLayout());
        previewPanel.add(scrollPane, BorderLayout.CENTER);
        previewPanel.add(saveButton, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, previewPanel, "Invoice Preview", JOptionPane.PLAIN_MESSAGE);
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            HotelModel model = new HotelModel(view);
            model.loadRoomCategories(guestName);
        });
        return backButton;
    }

    //Reloads the room list to reflect changes after booking
    private void refreshRoomList(String guestName) {
        HotelModel model = new HotelModel(view);
        model.loadAvailableRooms(bedCount, guestName);
    }
}
