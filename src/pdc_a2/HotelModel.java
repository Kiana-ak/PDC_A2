/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 64210
 */
public class HotelModel {
    private HotelListener listener;
    private HotelData data;
    private RoomDatabase roomDatabase;
    private HotelView view;

    public HotelModel(HotelView view) {
        this.view=view;
        this.roomDatabase = new RoomDatabase();
        this.data = new HotelData();
    }

    public void setListener(HotelListener listener) {
        this.listener = listener;
    }

    private void notifyListener() {
        if (listener != null) {
            listener.hotelUpdate(this.data);
        }
    }

public void loadRoomCategories() {
    // Create a new panel that shows 1-bed, 2-bed, 3-bed options
    RoomCategoryPanel categoryPanel = new RoomCategoryPanel(this);
    view.setContentPane(categoryPanel);
    view.revalidate();
}

public void loadAvailableRooms(int bedCount) {
    List<Room> filteredRooms = roomDatabase.getAvailableRooms().stream()
        .filter(r -> r.getBeds() == bedCount && !r.isBooked())
        .collect(Collectors.toList());

    RoomListPanel listPanel = new RoomListPanel(filteredRooms, bedCount, view);
    view.setContentPane(listPanel);
    view.revalidate();
}

    
}
