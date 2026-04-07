package com.krishnamurti.playground.lld.parkinglot;

import com.krishnamurti.playground.lld.parkinglot.controller.AdminController;
import com.krishnamurti.playground.lld.parkinglot.controller.EntryController;
import com.krishnamurti.playground.lld.parkinglot.controller.ExitController;
import com.krishnamurti.playground.lld.parkinglot.controller.dto.EntryResponse;
import com.krishnamurti.playground.lld.parkinglot.controller.dto.ExitResponse;
import com.krishnamurti.playground.lld.parkinglot.domain.Floor;
import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.Payment;
import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.repository.FloorRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.ParkingSlotRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.PricingRuleRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.ReceiptRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.TicketRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.impl.FloorInMemoryRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.impl.ParkingSlotInMemoryRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.impl.PricingRuleInMemoryRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.impl.ReceiptInMemoryRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.impl.TicketInMemoryRepository;
import com.krishnamurti.playground.lld.parkinglot.service.FloorService;
import com.krishnamurti.playground.lld.parkinglot.service.ParkingSlotService;
import com.krishnamurti.playground.lld.parkinglot.service.PricingRuleService;
import com.krishnamurti.playground.lld.parkinglot.service.RecieptService;
import com.krishnamurti.playground.lld.parkinglot.service.TicketService;
import com.krishnamurti.playground.lld.parkinglot.service.impl.FloorServiceImpl;
import com.krishnamurti.playground.lld.parkinglot.service.impl.ParkingSlotServiceImpl;
import com.krishnamurti.playground.lld.parkinglot.service.impl.PricingRuleServiceImpl;
import com.krishnamurti.playground.lld.parkinglot.service.impl.ReceiptServiceImpl;
import com.krishnamurti.playground.lld.parkinglot.service.impl.TicketServiceImpl;
import com.krishnamurti.playground.lld.parkinglot.strategy.PaymentStrategyFactory;
import com.krishnamurti.playground.lld.parkinglot.strategy.PricingStrategyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Main simulation class for the Parking Lot system.
 * This class demonstrates the initialization of the system (repositories, services, controllers)
 * and simulates the end-to-end flow of administrative setup, vehicle entry, and vehicle exit.
 */
public class ParkingLotSimulation {

    /**
     * Entry point for the parking lot simulation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Parking Lot Simulation Start-------");

        // Initialization Phase: Setting up repositories and services for parking slots.
        ParkingSlotRepository parkingSlotRepository = new ParkingSlotInMemoryRepository();
        ParkingSlotService parkingSlotService = new ParkingSlotServiceImpl(parkingSlotRepository);

        // Setting up floor management.
        FloorRepository floorRepository = new FloorInMemoryRepository();
        FloorService floorService = new FloorServiceImpl(floorRepository, parkingSlotService);

        // Setting up pricing rules management.
        PricingRuleRepository pricingRuleRepository = new PricingRuleInMemoryRepository();
        PricingRuleService pricingRuleService = new PricingRuleServiceImpl(pricingRuleRepository);

        // Setup the administrative controller to prepare the parking lot infrastructure.
        AdminController adminController = new AdminController(floorService, pricingRuleService);
        List<ParkingSlot> parkingSlots = new ArrayList<>();
        Floor floor = new Floor(parkingSlots);

        // Add various types of slots to the first floor.
        parkingSlots.add(new ParkingSlot(SlotType.BIKE, floor));
        parkingSlots.add(new ParkingSlot(SlotType.BIKE, floor));

        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor));
        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor));
        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor));

        parkingSlots.add(new ParkingSlot(SlotType.TRUCK, floor));

        // Create a second floor with additional slots.
        Floor floor1 = new Floor(parkingSlots);

        parkingSlots.add(new ParkingSlot(SlotType.BIKE, floor1));
        parkingSlots.add(new ParkingSlot(SlotType.BIKE, floor1));

        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor1));
        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor1));
        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor1));

        // Register floors using the admin controller.
        adminController.addFloor(floor);
        adminController.addFloor(floor1);

        // Define and register pricing rules for different billing types.
        PricingRule rule1 = new PricingRule(20.0, PricingRule.PricingType.FLAT_RATE);
        PricingRule rule2 = new PricingRule(2.0, PricingRule.PricingType.HOURLY);
        PricingRule rule3 = new PricingRule(30.0, PricingRule.PricingType.DAYS);

        adminController.addPricingRule(rule1);
        adminController.addPricingRule(rule2);
        adminController.addPricingRule(rule3);

        System.out.println("Admin setup complete -------------");

        // Operational Phase: Setting up entry and exit management.
        TicketRepository ticketRepository = new TicketInMemoryRepository();
        TicketService ticketService = new TicketServiceImpl(ticketRepository, pricingRuleRepository);
        EntryController entryController = new EntryController(parkingSlotService, ticketService);

        // Simulating vehicle entries.
        EntryResponse lc1 = entryController.createVehicleEntry("lc-1", SlotType.BIKE);
        processEntryResponse(lc1);

        EntryResponse lc2 = entryController.createVehicleEntry("lc-2", SlotType.CAR);
        processEntryResponse(lc2);

        EntryResponse lc3 = entryController.createVehicleEntry("lc-3", SlotType.TRUCK);
        processEntryResponse(lc3);

        EntryResponse lc4 = entryController.createVehicleEntry("lc-3", SlotType.TRUCK);
        processEntryResponse(lc4);

        // Setting up exit processing components.
        ReceiptRepository receiptRepository = new ReceiptInMemoryRepository();
        PricingStrategyFactory pricingStrategyFactory = new PricingStrategyFactory();
        PaymentStrategyFactory paymentStrategyFactory = new PaymentStrategyFactory();
        RecieptService recieptService = new ReceiptServiceImpl(receiptRepository, pricingStrategyFactory, paymentStrategyFactory);

        ExitController exitController = new ExitController(ticketService, parkingSlotService, recieptService);

        // Simulating vehicle exits.
        ExitResponse lc1r = exitController.exitRequest(lc1.getTicket().getTicketId());
        processExitResponse(lc1r);

        ExitResponse lc2r = exitController.exitRequest(lc2.getTicket().getTicketId());
        processExitResponse(lc2r);

        ExitResponse lc3r = exitController.exitRequest(lc3.getTicket().getTicketId());
        processExitResponse(lc3r);

        System.out.println("Parking Lot Simulation End-------");
    }

    /**
     * Processes the response from an exit request, simulating physical exit allowance.
     *
     * @param lc1r the exit response to process
     */
    private static void processExitResponse(ExitResponse lc1r) {
        if (lc1r.getReceipt().getPayment().getStatus() == Payment.Status.FAILED) {
            System.out.println("Payment failed, accept cash!!!");
        } else if (lc1r.getReceipt().getPayment().getStatus() == Payment.Status.SUCCESS) {
            System.out.println("Allow Exit!!");
        }
    }

    /**
     * Processes the response from an entry request, simulating entry allowance.
     *
     * @param response the entry response to process
     */
    private static void processEntryResponse(EntryResponse response) {
        if (response.getStatus() != EntryResponse.Status.ALLOTTED) {
            System.out.println(response.getMessage());
        }
    }
}
