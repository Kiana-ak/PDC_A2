/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 64210 HotelModel connects to the database, filter rooms by bed count
 * stores selected booking data and notifies the view, HotelListener
 */
public class HotelModel {

    private HotelListener listener;
    private HotelData data;
    private RoomDatabase roomDatabase;
    private HotelView view;
    private String guestName;


    // set up the model with refrence to the view
    public HotelModel(HotelView view) {
        this.view = view;
        this.roomDatabase = new RoomDatabase();
        this.data = new HotelData();
    }

    public String getGuestName() {
        return this.guestName;
    }

    // let the view listen for updates
    public void setListener(HotelListener listener) {
        this.listener = listener;
    }

    //tell the view model has updated data
    private void notifyListener() {
        if (listener != null) {
            listener.hotelUpdate(this.data);
        }
    }

    // show 1 to 3 beds options in panel
    public void loadRoomCategories(String guestName) {
        // Create a new panel that shows 1-bed, 2-bed, 3-bed options
        this.guestName = guestName;
        RoomCategoryPanel categoryPanel = new RoomCategoryPanel(this, guestName);
        view.setContentPane(categoryPanel);
        view.revalidate();
    }

    //show rooms
    public void loadAvailableRooms(int bedCount, String guestName) {
        List<Room> filteredRooms = roomDatabase.getAvailableRooms().stream()
                .filter(r -> r.getBeds() == bedCount && !r.isBooked())
                .collect(Collectors.toList());

        RoomListPanel listPanel = new RoomListPanel(filteredRooms, bedCount, guestName, view);
        view.setContentPane(listPanel);
        view.revalidate();
    }

    public HotelView getView() {
        return view;
    }

}
