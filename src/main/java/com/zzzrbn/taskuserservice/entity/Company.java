package com.zzzrbn.taskuserservice.entity;

import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Company {
	
    private Long id;
    private String name;
    private BigDecimal budget;
    private List<Long> usersIds;
    private List<Userrecord> users;
	
    @Override
	public int hashCode() {
		return Objects.hash(budget, name, users, usersIds);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(budget, other.budget) && Objects.equals(name, other.name)
				&& Objects.equals(users, other.users) && Objects.equals(usersIds, other.usersIds);
	}	
    
    
}
