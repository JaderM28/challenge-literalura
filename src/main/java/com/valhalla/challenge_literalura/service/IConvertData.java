package com.valhalla.challenge_literalura.service;

public interface IConvertData {

    <T> T obtenerDatos( String json, Class<T> tClass);
}
