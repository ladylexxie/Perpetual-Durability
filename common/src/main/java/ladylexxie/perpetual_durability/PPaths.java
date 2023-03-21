package ladylexxie.perpetual_durability;

import dev.architectury.platform.Platform;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.nio.file.Files;
import java.nio.file.Path;

public interface PPaths {
	MutableBoolean FIRST_RUN = new MutableBoolean(false);

	static Path dir(Path dir, boolean markFirstRun) {
		if ( Files.notExists(dir)) {
			try {
				Files.createDirectories(dir);

				if (markFirstRun) {
					FIRST_RUN.setTrue();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return dir;
	}

	public static final Path CONFIG = dir(Platform.getConfigFolder().resolve(PerpetualDurability.ID), true);
}
