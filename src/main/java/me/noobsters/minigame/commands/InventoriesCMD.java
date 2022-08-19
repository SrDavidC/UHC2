package me.noobsters.minigame.commands;

/*
@CommandPermission("uhc.inventory.sync")
@CommandAlias("inv-sync")
//public class InventoriesCMD extends BaseCommand {
    //private Gson gson = new Gson();

    /*
     * Mira :rat: tienes que hacer que puedas aplicar y guarda inventarios a otros
     * jugadores, no solo a ti mismo
     */ //all

    /*
    @Subcommand("set")
    public void setInventory(CommandSender sender, @Flags("other") Player player) {
        var serialized_inv = InventorySerialization.playerInventoryToBase64(player.getInventory());
        var rq = CondorAPI.builder().url(CondorAPI.CONDOR_URL + "/inventories/" + player.getUniqueId().toString())
                .post(RequestBody.create(CondorAPI.JSON, gson.toJson(serialized_inv))).build();
        try {
            var rcosq = CondorAPI.getClient().newCall(rq).execute();
            System.out.println(rcosq.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("all")
    @Subcommand("get")
    public void getInventory(CommandSender sender, @Flags("other") OfflinePlayer player) {
        try {
            var json = CondorAPI.makeJsonRequest(CondorAPI.builder()
                    .url(CondorAPI.CONDOR_URL + "/inventories/" + player.getUniqueId().toString()).get().build());

            var jsonInv = json.get("data").getAsJsonArray();

            var content = InventorySerialization.itemStackArrayFromBase64(jsonInv.get(0).getAsString());
            var armor = InventorySerialization.itemStackArrayFromBase64(jsonInv.get(1).getAsString());
            if (player.isOnline()) {
                var on = player.getPlayer();
                on.getInventory().setContents(content);
                on.getInventory().setArmorContents(armor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


     */
