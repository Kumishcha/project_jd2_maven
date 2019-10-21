package by.htp.jd2.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.jd2.command.impl.AuthorizationCommand;
import by.htp.jd2.command.impl.BackToOrderCommand;
import by.htp.jd2.command.impl.GoToClientPageCommand;
import by.htp.jd2.command.impl.GoToErrorPageCommand;
import by.htp.jd2.command.impl.GoToIndexPageCommand;
import by.htp.jd2.command.impl.GoToProductDetailsPageCommand;
import by.htp.jd2.command.impl.BlockUserAccountCommand;
import by.htp.jd2.command.impl.ChangeCountOfDrinkCommand;
import by.htp.jd2.command.impl.ChangePriceInStockCommand;
import by.htp.jd2.command.impl.ChangeRoleCommand;
import by.htp.jd2.command.impl.ChooseDrinkCommand;
import by.htp.jd2.command.impl.ChooseTypeOfCoffeeCommand;
import by.htp.jd2.command.impl.GoToAdminPageCommand;
import by.htp.jd2.command.impl.GoToAllProductsPageCommand;
import by.htp.jd2.command.impl.GoToAllUsersPageCommand;
import by.htp.jd2.command.impl.GoToRegistrationPageCommand;
import by.htp.jd2.command.impl.IncreaseQuantityInStockCommand;
import by.htp.jd2.command.impl.LocalCommand;
import by.htp.jd2.command.impl.MakeNewOrderCommand;
import by.htp.jd2.command.impl.NoSuchCommand;
import by.htp.jd2.command.impl.OrderCommand;
import by.htp.jd2.command.impl.OrderDetailsCommand;
import by.htp.jd2.command.impl.PaginationCommand;
import by.htp.jd2.command.impl.PayForOrderCommand;
import by.htp.jd2.command.impl.ProductDetailsCommand;
import by.htp.jd2.command.impl.RegistrationCommand;
import by.htp.jd2.command.impl.RemoveDrinkCommand;
import by.htp.jd2.command.impl.AddMoneyCommand;
import by.htp.jd2.command.impl.AddAmountCommand;
import by.htp.jd2.command.impl.UnblockUserAccountCommand;
import by.htp.jd2.command.impl.UserDetailsCommand;
import by.htp.jd2.command.impl.ViewAllStockCommand;
import by.htp.jd2.command.impl.ViewAllUrerOrdersCommand;
import by.htp.jd2.command.impl.ViewAllUrersCommand;

public class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();

	private Map<CommandName, Command> commands = new HashMap<>();

	private CommandHelper() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		commands.put(CommandName.VIEW_ALL_STOCK, new ViewAllStockCommand());
		commands.put(CommandName.INCREASE_QUANTITY_IN_STOCK, new IncreaseQuantityInStockCommand());
		commands.put(CommandName.CHANGE_PRICE, new ChangePriceInStockCommand());
		commands.put(CommandName.VIEW, new ViewAllUrersCommand());
		commands.put(CommandName.USER_DETAILS, new UserDetailsCommand());
		commands.put(CommandName.PRODUCT_DETAILS, new ProductDetailsCommand());
		commands.put(CommandName.CHANGE_ROLE, new ChangeRoleCommand());
		commands.put(CommandName.BLOCK, new BlockUserAccountCommand());
		commands.put(CommandName.UNBLOCK, new UnblockUserAccountCommand());
		commands.put(CommandName.CHOOSE_A_DRINK, new ChooseDrinkCommand());
		commands.put(CommandName.CHOOSE_A_TYPE_OF_COFFEE, new ChooseTypeOfCoffeeCommand());
		commands.put(CommandName.ADD_MONEY, new AddMoneyCommand());
		commands.put(CommandName.ADD_AMOUNT, new AddAmountCommand());
		commands.put(CommandName.CHANGE_COUNT_OF_DRINK, new ChangeCountOfDrinkCommand());
		commands.put(CommandName.REMOVE_DRINK, new RemoveDrinkCommand());
		commands.put(CommandName.ORDER, new OrderCommand());
		commands.put(CommandName.BACK_TO_ORDER, new BackToOrderCommand());
		commands.put(CommandName.PAY_FOR_ORDER, new PayForOrderCommand());
		commands.put(CommandName.MAKE_NEW_ORDER, new MakeNewOrderCommand());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPageCommand());
		commands.put(CommandName.GO_TO_CLIENT_PAGE, new GoToClientPageCommand());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
		commands.put(CommandName.GO_TO_VIEW_ALL_USERS_PAGE, new GoToAllUsersPageCommand());
		commands.put(CommandName.GO_TO_VIEW_ALL_PRODUCTS_PAGE, new GoToAllProductsPageCommand());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		commands.put(CommandName.GO_TO_PRODUCT_DETAILS_PAGE, new GoToProductDetailsPageCommand());
		commands.put(CommandName.VIEW_ALL_USER_ORDERS, new ViewAllUrerOrdersCommand());
		commands.put(CommandName.ORDER_DETAILS, new OrderDetailsCommand());
		commands.put(CommandName.LOCAL, new LocalCommand());
		commands.put(CommandName.PAGINATION, new PaginationCommand());

	}

	public static CommandHelper getInstance() {
		return instance;
	}

	public Command getCommand(String commandName) {

		CommandName name;
		Command command;

		if (findInEnum(commandName)) {
			name = CommandName.valueOf(commandName.toUpperCase().replaceAll("-", "_"));
			command = commands.get(name);
		} else {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		return command;

//		if (null != name) {
//			command = commands.get(name);
//		} else {
//			command = commands.get(CommandName.NO_SUCH_COMMAND);
//		}
//		return command;
	}

	private boolean findInEnum(String commandName) {

		for (CommandName n : CommandName.values()) {
			if (n.name().equals(commandName.toUpperCase().replaceAll("-", "_"))) {
				return true;
			}
		}
		return false;
	}

}
