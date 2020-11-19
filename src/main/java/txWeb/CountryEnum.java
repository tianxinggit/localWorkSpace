package txWeb;
/**  
 * @author txpc
 * @date 2020年4月26日 下午3:18:36 
 */
public enum CountryEnum {

	one(1,"齐国"),
	two(2,"楚国"),
	three(3,"燕国"),
	four(4,"赵国"),
	five(5,"魏国"),
	six(6,"韩国"),
	seven(7,"秦国"),
	;
	public int val;
	public String name;
	
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private CountryEnum(int val, String name) {
		this.val = val;
		this.name = name;
	}
	public static CountryEnum foreach_CountryEnum(int i){
		CountryEnum[] ems = CountryEnum.values();
		for (CountryEnum countryEnum : ems) {
			if(countryEnum.val == i){
				return countryEnum;
			}
		}
		return null;
	}
}
