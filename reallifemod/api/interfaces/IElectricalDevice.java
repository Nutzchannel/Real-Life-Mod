package de.ItsAMysterious.mods.reallifemod.api.interfaces;

public interface IElectricalDevice {
double MinVoltage();
double MaxVoltage();
boolean canExplode();
boolean isProtected();
boolean hasBattery();
}

