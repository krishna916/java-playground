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

public class ParkingLotSimulation {

    public static void main(String[] args) {
        System.out.println("Parking Lot Simulation Start-------");


        ParkingSlotRepository parkingSlotRepository = new ParkingSlotInMemoryRepository();
        ParkingSlotService parkingSlotService = new ParkingSlotServiceImpl(parkingSlotRepository);


        FloorRepository floorRepository = new FloorInMemoryRepository();
        FloorService floorService = new FloorServiceImpl(floorRepository, parkingSlotService);

        PricingRuleRepository pricingRuleRepository = new PricingRuleInMemoryRepository();
        PricingRuleService pricingRuleService = new PricingRuleServiceImpl(pricingRuleRepository);


        AdminController adminController = new AdminController(floorService, pricingRuleService);
        List<ParkingSlot> parkingSlots = new ArrayList<>();
        Floor floor = new Floor(parkingSlots);

        parkingSlots.add(new ParkingSlot(SlotType.BIKE, floor));
        parkingSlots.add(new ParkingSlot(SlotType.BIKE, floor));

        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor));
        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor));
        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor));

        parkingSlots.add(new ParkingSlot(SlotType.TRUCK, floor));

        Floor floor1 = new Floor(parkingSlots);

        parkingSlots.add(new ParkingSlot(SlotType.BIKE, floor1));
        parkingSlots.add(new ParkingSlot(SlotType.BIKE, floor1));

        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor1));
        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor1));
        parkingSlots.add(new ParkingSlot(SlotType.CAR, floor1));

        adminController.addFloor(floor);
        adminController.addFloor(floor1);

        PricingRule rule1 = new PricingRule(20.0, PricingRule.PricingType.FLAT_RATE);
        PricingRule rule2 = new PricingRule(2.0, PricingRule.PricingType.HOURLY);
        PricingRule rule3 = new PricingRule(30.0, PricingRule.PricingType.DAYS);

        adminController.addPricingRule(rule1);
        adminController.addPricingRule(rule2);
        adminController.addPricingRule(rule3);

        System.out.println("Admin setup complete -------------");

        TicketRepository ticketRepository = new TicketInMemoryRepository();

        TicketService ticketService = new TicketServiceImpl(ticketRepository, pricingRuleRepository);

        EntryController entryController = new EntryController(parkingSlotService, ticketService);

        EntryResponse lc1 = entryController.createVehicleEntry("lc-1", SlotType.BIKE);
        processEntryResponse(lc1);

        EntryResponse lc2 = entryController.createVehicleEntry("lc-2", SlotType.CAR);
        processEntryResponse(lc2);

        EntryResponse lc3 = entryController.createVehicleEntry("lc-3", SlotType.TRUCK);
        processEntryResponse(lc3);

        EntryResponse lc4 = entryController.createVehicleEntry("lc-3", SlotType.TRUCK);
        processEntryResponse(lc4);

        ReceiptRepository receiptRepository = new ReceiptInMemoryRepository();
        PricingStrategyFactory pricingStrategyFactory = new PricingStrategyFactory();
        PaymentStrategyFactory paymentStrategyFactory = new PaymentStrategyFactory();
        RecieptService recieptService = new ReceiptServiceImpl(receiptRepository, pricingStrategyFactory, paymentStrategyFactory);

        ExitController exitController = new ExitController(ticketService, parkingSlotService, recieptService);

        ExitResponse lc1r = exitController.exitRequest(lc1.getTicket().getTicketId());
        processExitResponse(lc1r);

        ExitResponse lc2r = exitController.exitRequest(lc2.getTicket().getTicketId());
        processExitResponse(lc2r);

        ExitResponse lc3r = exitController.exitRequest(lc3.getTicket().getTicketId());
        processExitResponse(lc3r);

//        ExitResponse lc4r = exitController.exitRequest(lc4.getTicket().getTicketId());
//        processExitResponse(lc4r);


        System.out.println("Parking Lot Simulation End-------");
    }

    private static void processExitResponse(ExitResponse lc1r) {
        if (lc1r.getReceipt().getPayment().getStatus() == Payment.Status.FAILED) {
            System.out.println("Payment failed, accept cash!!!");
        } else if (lc1r.getReceipt().getPayment().getStatus() == Payment.Status.SUCCESS) {
            System.out.println("Allow Exit!!");
        }
    }

    private static void processEntryResponse(EntryResponse response) {
        if (response.getStatus() != EntryResponse.Status.ALLOTTED) {
            System.out.println(response.getMessage());
        }
    }
}
