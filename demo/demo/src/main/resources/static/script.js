const calendar = new DayPilot.Calendar("calendar", {
  viewType: "Resources",
  columns: [
    { name: "Room 1", id: "R1" },
    { name: "Room 2", id: "R2" },
    { name: "Room 3", id: "R3" },
    { name: "Room 4", id: "R4" },
  ]
});
calendar.init();
