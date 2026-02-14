/**
 * Seven Things Theme JavaScript
 * Minimal JS for mobile navigation toggle close-on-click behavior
 */

(function() {
  'use strict';

  // Close mobile nav when clicking a navigation link
  document.addEventListener('DOMContentLoaded', function() {
    var navToggle = document.getElementById('nav-toggle');
    var navLinks = document.querySelectorAll('.sidebar a');

    navLinks.forEach(function(link) {
      link.addEventListener('click', function() {
        if (navToggle && navToggle.checked) {
          navToggle.checked = false;
        }
      });
    });
  });
})();
