package com.abubakar.dto;

public enum AccountStatus {
	
	PENDING_VERIFICATION, // Account is created but not yet verified
	ACTIVE, // Account is active and good standing
	SUSPENDED, // Account is temporarily suspended, possibly due to violations
	DEACTIVATED,// Account is deactivated, user may have chosen to deactivate
	BANNED,// Account is prmanently banned due to severe violations
	CLOSED // Account is permanently closed possibly by user request

}
