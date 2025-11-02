/**
 * Admin Dashboard Script
 * Author: Anushka
 * Description: Handles admin dashboard actions like approval and rejection
 */

document.addEventListener("DOMContentLoaded", () => {
  console.log("✅ Admin Dashboard Loaded Successfully");

  const approveButtons = document.querySelectorAll(".btn-approve");
  const rejectButtons = document.querySelectorAll(".btn-reject");

  approveButtons.forEach((button) => {
    button.addEventListener("click", () => {
      const laptopId = button.dataset.id;
      console.log(`Approved Laptop ID: ${laptopId}`);
      alert(`Laptop ID ${laptopId} approved!`);
      // TODO: Make API call to backend here
    });
  });

  rejectButtons.forEach((button) => {
    button.addEventListener("click", () => {
      const laptopId = button.dataset.id;
      console.log(`Rejected Laptop ID: ${laptopId}`);
      alert(`Laptop ID ${laptopId} rejected!`);
      // TODO: Make API call to backend here
    });
  });
});
