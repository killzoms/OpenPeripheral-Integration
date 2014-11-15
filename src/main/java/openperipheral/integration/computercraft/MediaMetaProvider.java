package openperipheral.integration.computercraft;

import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import openmods.utils.ReflectionHelper;
import openperipheral.api.helpers.ItemStackMetaProviderSimple;

import com.google.common.collect.Maps;

import dan200.computercraft.api.media.IMedia;

@SuppressWarnings("serial")
public class MediaMetaProvider extends ItemStackMetaProviderSimple<Item> {

	@Override
	public String getKey() {
		return "disk";
	}

	@Override
	public Object getMeta(Item target, ItemStack stack) {
		if (target instanceof IMedia) return addDiskInfo(stack, (IMedia)target);

		IMedia media = ReflectionHelper.callStatic(ModuleComputerCraft.API_CLASS.get(), "getMedia", stack);
		if (media != null) return addDiskInfo(stack, media);

		return null;
	}

	private static Map<String, Object> addDiskInfo(ItemStack stack, IMedia item) {
		Map<String, Object> diskInfo = Maps.newHashMap();
		String label = item.getLabel(stack);
		if (label != null) diskInfo.put("label", label);

		String record = item.getAudioRecordName(stack);
		if (record != null) diskInfo.put("record", record);

		return diskInfo;
	}

}
