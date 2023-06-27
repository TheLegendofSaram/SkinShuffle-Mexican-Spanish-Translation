/*
 *
 *     Copyright (C) 2023 Calum (mineblock11), enjarai
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 */

package com.mineblock11.skinshuffle.client.gui;

import com.mineblock11.skinshuffle.client.config.SkinShuffleConfig;
import com.mineblock11.skinshuffle.networking.ClientSkinHandling;
import com.mineblock11.skinshuffle.util.NetworkingUtil;
import com.mineblock11.skinshuffle.util.ToastHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class GeneratedScreens {
    public static Screen getConfigScreen(Screen parent) {
        return SkinShuffleConfig.getInstance().generateScreen(parent);
    }

    public static Screen getReconnectScreen(Screen target) {
        MinecraftClient client = MinecraftClient.getInstance();
        return new ConfirmScreen((boolean result) -> {
            if(result) {
                NetworkingUtil.handleReconnect(client);
            } else {
                if (!ClientSkinHandling.isInstalledOnServer()) {
                    ToastHelper.showRefusedReconnectToast();
                    ClientSkinHandling.setReconnectRequired(true);
                }

                client.setScreen(target);
            }
        }, Text.translatable("skinshuffle.reconnect.title",
                client.isInSingleplayer() ? I18n.translate("skinshuffle.reconnect.c_region") : I18n.translate("skinshuffle.reconnect.c_reconnect")).formatted(Formatting.RED, Formatting.BOLD),
                Text.translatable("skinshuffle.reconnect.message",
                        client.isInSingleplayer() ? I18n.translate("skinshuffle.reconnect.rejoin") : I18n.translate("skinshuffle.reconnect.reconnect_to"),
                        client.isInSingleplayer() ? I18n.translate("skinshuffle.reconnect.world") : client.isConnectedToRealms() ? I18n.translate("skinshuffle.reconnect.realm") : I18n.translate("skinshuffle.reconnect.server"),
                        client.isInSingleplayer() ? I18n.translate("skinshuffle.reconnect.rejoin") : I18n.translate("skinshuffle.reconnect.reconnect")));
    }
}
