(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory();
	else if(typeof define === 'function' && define.amd)
		define([], factory);
	else if(typeof exports === 'object')
		exports["ShortcutButtonsPlugin"] = factory();
	else
		root["ShortcutButtonsPlugin"] = factory();
})(window, function() {
return /******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/index.ts");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/index.ts":
/*!**********************!*\
  !*** ./src/index.ts ***!
  \**********************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";

/*
 * Copyright (c) 2018 João Morais under the MIT license.
 * https://github.com/jcsmorais/shortcut-buttons-flatpickr/
 */
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ShortcutButtonsPlugin = void 0;
var defaultConfig = {
    theme: 'light',
};
/**
 * List of attributes that can be set through button's options.
 */
var supportedAttributes = new Set([
    'accesskey',
    'aria-label',
]);
/**
 * Adds shortcut buttons to flatpickr providing users an alternative way to interact with the datetime picker.
 *
 * Example usage:
 *
 * ```ts
 * flatpickr('.target-input-element', {
 *     // ...
 *     plugins: [ShortcutButtonsPlugin({
 *         button: {
 *             label: 'The Beginning Of Time',
 *         },
 *         onClick: (index: number, fp: Flatpickr) => {
 *             // Do something when a button is clicked
 *         },
 *         theme: 'light',
 *     })],
 * })
 * ```
 *
 * @param config Configuration options.
 *
 * Supported options are:
 *    `button`: button(s).
 *    `button.attributes`: button's attributes.
 *    `button.label`: button's label.
 *    `label`: label including a sentence stating that the user can use the calendar controls or one of the buttons.
 *    `onClick`: callback(s) invoked when plugin's buttons are clicked.
 *    `theme`: flatpickr's theme.
 */
function ShortcutButtonsPlugin(config) {
    var cfg = __assign(__assign({}, defaultConfig), config);
    return function (fp) {
        /**
         * Element that wraps this plugin's dependent elements.
         */
        var wrapper;
        /**
         * Handles click events on plugin's button.
         */
        function onClick(event) {
            event.stopPropagation();
            event.preventDefault();
            var target = event.target;
            if (target.tagName.toLowerCase() !== 'button' || typeof cfg.onClick === 'undefined') {
                return;
            }
            var index = parseInt(target.dataset.index, 10);
            var callbacks = Array.isArray(cfg.onClick) ?
                cfg.onClick :
                [cfg.onClick];
            for (var _i = 0, callbacks_1 = callbacks; _i < callbacks_1.length; _i++) {
                var callback = callbacks_1[_i];
                if (typeof callback === 'function') {
                    callback(index, fp);
                }
            }
        }
        /**
         * Handles key down events on plugin's button.
         */
        function onKeyDown(event) {
            var target = event.target;
            if (event.key !== 'Tab' || target.tagName.toLowerCase() !== 'button') {
                return;
            }
            if ((event.shiftKey && !target.previousSibling) || (!event.shiftKey && !target.nextSibling)) {
                event.preventDefault();
                fp.element.focus();
            }
        }
        /**
         * Set given button's attributes.
         */
        function setButtonsAttributes(button, attributes) {
            Object.keys(attributes).filter(function (attribute) { return supportedAttributes.has(attribute); }).forEach(function (key) { return button.setAttribute(key, attributes[key]); });
        }
        return {
            /**
             * Initialize plugin.
             */
            onReady: function () {
                wrapper = document.createElement('div');
                wrapper.classList.add('shortcut-buttons-flatpickr-wrapper', cfg.theme);
                if (typeof cfg.label !== 'undefined' && cfg.label.length) {
                    var label = document.createElement('div');
                    label.classList.add('shortcut-buttons-flatpickr-label');
                    label.textContent = cfg.label;
                    wrapper.appendChild(label);
                }
                var buttons = document.createElement('div');
                buttons.classList.add('shortcut-buttons-flatpickr-buttons');
                (Array.isArray(cfg.button) ? cfg.button : [cfg.button]).forEach(function (b, index) {
                    var button = document.createElement('button');
                    button.type = 'button';
                    button.classList.add('shortcut-buttons-flatpickr-button');
                    button.textContent = b.label;
                    button.dataset.index = String(index);
                    if (typeof b.attributes !== 'undefined') {
                        setButtonsAttributes(button, b.attributes);
                    }
                    buttons.appendChild(button);
                    fp.pluginElements.push(button);
                });
                wrapper.appendChild(buttons);
                fp.calendarContainer.appendChild(wrapper);
                wrapper.addEventListener('click', onClick);
                wrapper.addEventListener('keydown', onKeyDown);
            },
            /**
             * Clean up before flatpickr is destroyed.
             */
            onDestroy: function () {
                wrapper.removeEventListener('keydown', onKeyDown);
                wrapper.removeEventListener('click', onClick);
                wrapper = undefined;
            },
        };
    };
}
exports.ShortcutButtonsPlugin = ShortcutButtonsPlugin;


/***/ })

/******/ })["ShortcutButtonsPlugin"];
});