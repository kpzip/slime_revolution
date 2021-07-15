package io.github.kpzip.slimerevolution.core.util;

public final class TextFormattingHelper {
	
	private TextFormattingHelper() {
        throw new IllegalAccessError("Class only uses static methods");
    }
	
	public String formatText(String text, String keyword, Object... objs) {
		String str = "";
		String[] split = text.split(keyword);
		for (int i = 0; i < split.length; i++) {
			str += split[i];
			if (i < objs.length) {
				str += objs[i].toString();
			}
		}
		return str;
	}

}
